package com.example.microgreens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddScheduleActivity extends AppCompatActivity implements RecyclerViewInterface {

    private TextInputEditText plantNameET;
    private TextInputEditText plantDateET;

    private Task_RecyclerViewAdapter adapter;

    private PlantModel plantModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_schedule);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("test", String.valueOf(MainActivity.plantModels.size()));
        Log.d("test", String.valueOf(getIntent().getIntExtra("pos", -1)));

        if (!MainActivity.plantModels.isEmpty()) {
            plantModel = MainActivity.plantModels.get(getIntent().getIntExtra("pos", -1));
        } else {
            MainActivity.plantModels.add(new PlantModel());

            plantModel = MainActivity.plantModels.get(0);

            plantModel.getTasks().add(new PlantTask());
        }

        RecyclerView recyclerView = findViewById(R.id.mainRV2);

        plantNameET = findViewById(R.id.plantNameET);
        plantDateET = findViewById(R.id.plantDateET);

        plantNameET.setText(plantModel.getName());
        plantDateET.setText(plantModel.getDate());

        adapter = new Task_RecyclerViewAdapter(this, plantModel, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addTaskFAB = findViewById(R.id.addTaskFAB);

        addTaskFAB.setOnClickListener((v) -> {
            plantModel.setName(String.valueOf(plantNameET.getText()));
            plantModel.getTasks().add(new PlantTask());
            adapter.notifyItemInserted(plantModel.getTasks().size() - 1);
        });

        FloatingActionButton finishedFAB = findViewById(R.id.finishedFAB);

        finishedFAB.setOnClickListener((v) -> {
            plantModel.setName(String.valueOf(plantNameET.getText()));
            plantModel.setDate(String.valueOf(plantDateET.getText()));

            startActivity(new Intent(this, MainActivity.class));
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("modelPos", getIntent().getIntExtra("pos", -1));
        intent.putExtra("taskPos", position);
        startActivity(intent);
    }

    @Override
    public void onRemoveClick(int position) {
        plantModel.getTasks().remove(position);
        adapter.notifyItemRemoved(position);
    }
}