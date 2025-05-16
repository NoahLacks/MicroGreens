package com.example.microgreens;

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

        if (!MainActivity.plantModels.isEmpty()) {
            plantModel = MainActivity.plantModels.get(getIntent().getIntExtra("pos", -1));
        } else {
            MainActivity.plantModels.add(new PlantModel());

            plantModel = MainActivity.plantModels.get(0);

            plantModel.getTasks().add(new PlantTask());
        }

        RecyclerView recyclerView = findViewById(R.id.mainRV2);

        plantNameET = findViewById(R.id.plantNameET);

        plantNameET.setText(plantModel.getName());

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
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onRemoveClick(int position) {
        plantModel.getTasks().remove(position);
        adapter.notifyItemRemoved(position);
    }
}