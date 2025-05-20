package com.example.microgreens;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Plant_RecyclerViewAdapter extends RecyclerView.Adapter<Plant_RecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    private Context context;
    private ArrayList<PlantModel> plantModels;
    private Plant_RecyclerViewAdapter adapter;

    public Plant_RecyclerViewAdapter(Context context, ArrayList<PlantModel> plantModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.plantModels = plantModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Plant_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.plant_card_row, parent, false);

        return new Plant_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Plant_RecyclerViewAdapter.MyViewHolder holder, int position) {
        Log.d("test", "tester");
        holder.nameTV.setText(plantModels.get(position).getName());
        holder.dateTV.setText(plantModels.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        Log.d("test", String.valueOf(plantModels.size()));
        return plantModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView dateTV;

        CardView cardCV;

        ImageButton removeIB;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            cardCV = itemView.findViewById(R.id.cardView);
            removeIB = itemView.findViewById(R.id.imageButton);

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
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION)
                            recyclerViewInterface.onRemoveClick(pos);
                    }
                }
            });
        }
    }
}
