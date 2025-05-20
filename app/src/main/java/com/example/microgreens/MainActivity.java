package com.example.microgreens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    public static ArrayList<PlantModel> plantModels;

    private Plant_RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (plantModels == null) {
            /*SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            Gson gson = new Gson();
            String j = sharedPreferences.getString("Plants", gson.toJson(new PlantModel()));
            plantModels = gson.fromJson(j, ArrayList.class);
            Log.d("test", String.valueOf(plantModels));

            Failed way to save objects
            */

            plantModels = new ArrayList<>();
            plantModels.add(new PlantModel());
            Log.d("test", "added model");
            plantModels.get(0).setName("david");
            Log.d("test", "set david");
            Log.d("test", String.valueOf(plantModels));

        }

        RecyclerView recyclerView = findViewById(R.id.mainRV);

        adapter = new Plant_RecyclerViewAdapter(this, plantModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addScheduleButton = findViewById(R.id.addScheduleFAB);

        addScheduleButton.setOnClickListener((v) -> {
            MainActivity.plantModels.add(new PlantModel());
            Intent intent = new Intent(this, AddScheduleActivity.class);
            intent.putExtra("pos", plantModels.size() - 1);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, AddScheduleActivity.class);
        intent.putExtra("pos", position);
        startActivity(intent);
    }

    @Override
    public void onRemoveClick(int position) {
        plantModels.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void openActivity(Class<? extends AppCompatActivity> activity) {
        startActivity(new Intent(this, activity));
    }

    /*
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(plantModels);
        preferencesEditor.putString("Plants", json);
        preferencesEditor.apply();
    }
    */
}