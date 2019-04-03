package com.app.shaalastic.Tower;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.app.shaalastic.Tower.viewTower;
import com.app.shaalastic.ChildActivity;
import com.app.shaalastic.R;

import java.util.ArrayList;

public class TowerAdapter extends RecyclerView.Adapter<TowerAdapter.Holder> {
    ArrayList<TowerData> tower;
    Activity activity;
    TowerAdapter(Activity activity,ArrayList<TowerData>tower){
        this.tower=tower;
        this.activity=activity;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tower,viewGroup,false);
        return new Holder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final TowerData data=tower.get(i);
        holder.name.setText(data.getName());
        holder.viewTower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity, ChildActivity.class);
                i.putExtra("fragment",ChildActivity.VIEW_TOWER);
                i.putExtra("title",data.getName());
                activity.startActivity(i);
            }
        });
    }

    public void viewTower(){

    }

    @Override
    public int getItemCount() {
        return tower.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout linearLayout;
        Button viewTower;
        public Holder(@NonNull View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.towerOptions);
            name=(TextView)itemView.findViewById(R.id.towerName);
            viewTower=(Button)itemView.findViewById(R.id.viewTower);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (linearLayout.getVisibility()==View.VISIBLE){
                        linearLayout.setVisibility(View.GONE);
                    }
                    else {
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}

