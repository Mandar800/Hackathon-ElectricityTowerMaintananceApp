package com.app.shaalastic.Assignments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.Holder> {
    ArrayList<assignmentData> assignments;
    Activity activity;
    AssignmentAdapter(Activity activity,ArrayList<assignmentData> assignments){
        this.assignments=assignments;
        this.activity=activity;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assignment,viewGroup,false);
        return new Holder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final assignmentData data=assignments.get(i);
        holder.name.setText(data.getName());
        holder.subject.setText(data.getSubject());
        holder.viewAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity, ChildActivity.class);
                i.putExtra("fragment",ChildActivity.VIEW_ASSIGNMENT);
                i.putExtra("title",data.getName());
                activity.startActivity(i);
            }
        });
    }

    public void viewAssignment(){

    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        TextView subject;
        LinearLayout linearLayout;
        Button viewAssignment;
        public Holder(@NonNull View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.assignmentOptions);
            name=(TextView)itemView.findViewById(R.id.assignmentName);
            subject=(TextView)itemView.findViewById(R.id.assignmentSubject);
            viewAssignment=(Button)itemView.findViewById(R.id.viewAssignment);
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
