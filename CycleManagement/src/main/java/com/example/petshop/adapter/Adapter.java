package com.example.petshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petshop.R;
import com.example.petshop.model.Cycle;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Cycle> animals;
    Context context;


    public class ViewHolder extends  RecyclerView.ViewHolder {
        private TextView title, subtitle;
        public ImageView icon;
//        private LinearLayout main;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
//            subtitle = itemView.findViewById(R.id.subtitle);
            icon = itemView.findViewById(R.id.icon);
//            main = itemView.findViewById(R.id.main);
//            main.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    public Adapter(ArrayList<Cycle> animals, Context context) {
        this.animals = animals;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.my_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder .title.setText(animals.get(i).getName());
//        viewHolder.subtitle.setText(animals.get(i).getId());
        viewHolder.icon.setImageResource(animals.get(i).getImageId());
    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Adapter adapter, int i) {
//
//    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}
