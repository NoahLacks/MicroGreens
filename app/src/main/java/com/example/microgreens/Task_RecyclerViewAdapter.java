package com.example.microgreens;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Task_RecyclerViewAdapter extends RecyclerView.Adapter<Task_RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private PlantModel plantModel;
    private RecyclerViewInterface recyclerViewInterface;

    private ArrayList<EditText> taskNames;

    public Task_RecyclerViewAdapter(Context context, PlantModel plantModel, RecyclerViewInterface recyclerViewInterface) {

        this.context = context;
        this.plantModel = plantModel;
        this.recyclerViewInterface = recyclerViewInterface;
        Log.d("test", "adapter was created");
    }

    @NonNull
    @Override
    public Task_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_card_row, parent, false);

        return new MyViewHolder(view, recyclerViewInterface, plantModel);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlantTask task = plantModel.getTasks().get(position);
        holder.taskDaysTV.setText(task.getTime() == 0 ? "" : String.valueOf(task.getTime()));
        holder.taskNameTV.setText(plantModel.getTasks().get(position).getName());
        holder.taskNoteTV.setText(plantModel.getTasks().get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return plantModel.getTasks().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageButton removeIB;

        private TextView taskDaysTV;
        private TextView taskNameTV;
        private TextView taskNoteTV;

        private PlantModel plantModel;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface, PlantModel plantModel) {
            super(itemView);

            this.plantModel = plantModel;

            removeIB = itemView.findViewById(R.id.imageButton);

            taskDaysTV = itemView.findViewById(R.id.daysTV);

            taskNameTV = itemView.findViewById(R.id.taskNameTV);

            taskNoteTV = itemView.findViewById(R.id.notesTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION)
                            recyclerViewInterface.onRemoveClick(pos);
                    }
                }
            });
        }
    }
}