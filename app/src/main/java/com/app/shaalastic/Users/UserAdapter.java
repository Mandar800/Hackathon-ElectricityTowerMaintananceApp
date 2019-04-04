package com.app.shaalastic.Users;

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

public class UserAdapter extends Adapter<UserAdapter.Holder> {
    ArrayList<UserData> user;
    Activity activity;
    UserAdapter(Activity activity, ArrayList<UserData> user) {
        this.user = user;
        this.activity = activity;

    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user,viewGroup,false);
        return new Holder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final UserData data=user.get(i);
        holder.name.setText(data.getName());
        holder.uid.setText(data.getUid());
        holder.viewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity, ChildActivity.class);
                i.putExtra("fragment",ChildActivity.VIEW_USER);
                i.putExtra("name",data.getName());
                activity.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return user.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        TextView uid;
        LinearLayout linearLayout;
        Button viewUser;
        public Holder(@NonNull View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.userOptions);
            name=(TextView)itemView.findViewById(R.id.userName);
            uid=(TextView)itemView.findViewById(R.id.userRollno);
            viewUser=(Button)itemView.findViewById(R.id.viewUser);
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