package com.app.shaalastic.Components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shaalastic.ChildActivity;
import com.app.shaalastic.R;

import java.util.ArrayList;
import java.util.List;

public class ComponentAdapter extends Adapter<ComponentAdapter.Holder> {
    ArrayList<ComponentData> component;
    Activity activity;
    ComponentAdapter(Activity activity, ArrayList<ComponentData> component) {
        this.component = component;
        this.activity = activity;

    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_component,viewGroup,false);
        return new Holder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final ComponentData data=component.get(i);
        holder.name.setText(data.getName());
        holder.viewComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity, ChildActivity.class);
                i.putExtra("fragment",ChildActivity.VIEW_COMPONENT);
                i.putExtra("name",data.getName());
                activity.startActivity(i);
            }
        });
    }
    public void viewComponent(){

    }
    @Override
    public int getItemCount() {
        return component.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout linearLayout;
        Button viewComponent;
        public Holder(@NonNull View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.componentOptions);
            name=(TextView)itemView.findViewById(R.id.componentName);
            viewComponent=(Button)itemView.findViewById(R.id.viewComponent);
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