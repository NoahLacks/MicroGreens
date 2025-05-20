package com.example.microgreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        PlantTask task = MainActivity.plantModels.get(intent.getIntExtra("modelPos", -1))
                .getTasks().get(intent.getIntExtra("taskPos", -1));

        EditText taskNameET = findViewById(R.id.taskNameET);
        EditText daysET = findViewById(R.id.daysET);
        EditText notesET = findViewById(R.id.notesET);

        Button addTaskButton = findViewById(R.id.addTaskButton);

        addTaskButton.setOnClickListener((v) -> {
            try {
                task.setName(taskNameET.getText().toString());
                task.setTime(Integer.parseInt(daysET.getText().toString()));
                task.setNote(notesET.getText().toString());

                Intent addIntent = new Intent(this, AddScheduleActivity.class);
                addIntent.putExtra("pos", intent.getIntExtra("modelPos", -1));
                startActivity(addIntent);
            } catch (NumberFormatException ignored) {

            }
        });

        FloatingActionButton backFAB = findViewById(R.id.backFAB);

        backFAB.setOnClickListener((v) -> {
            Intent addIntent = new Intent(this, AddScheduleActivity.class);
            addIntent.putExtra("pos", intent.getIntExtra("modelPos", -1));
            startActivity(addIntent);
        });
    }
}