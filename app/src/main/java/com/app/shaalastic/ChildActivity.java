package com.app.shaalastic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.app.shaalastic.Assignments.CreateAssignment;
import com.app.shaalastic.Assignments.viewAssignment;
import com.app.shaalastic.Components.CreateComponent;
import com.app.shaalastic.Components.viewComponent;
import com.app.shaalastic.Tower.CreateTower;
import com.app.shaalastic.Tower.viewTower;
import com.app.shaalastic.Users.CreateUser;

public class ChildActivity extends AppCompatActivity {

    public static int CREATE_ASSIGNMENT=200;
    public static int VIEW_ASSIGNMENT=201;
    public static int CREATE_USER=300;
    public static int VIEW_USER=301;
    public static int CREATE_COMPONENT=400;
    public static int VIEW_COMPONENT=401;
    public static int CREATE_TOWER=500;
    public static int VIEW_TOWER=501;


    private int fragId;
    private Intent intent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent=getIntent();
        fragId=intent.getIntExtra("fragment",0);
        setup(fragId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void setup(int fragId){
        Fragment fragment=null;
        String title=null;
         if(fragId==CREATE_ASSIGNMENT){
            fragment= new CreateAssignment();
            title="Create Assignment";
        }
        else if(fragId==VIEW_ASSIGNMENT){
            fragment= new viewAssignment();
            Bundle bundle=new Bundle();
            bundle.putString("title",intent.getStringExtra("title"));
            fragment.setArguments(bundle);
            title="Assignment";
        }
        else if(fragId==CREATE_USER){
            fragment= new CreateUser();
            title="Create User";
        }
         else if(fragId==VIEW_COMPONENT){
             fragment= new viewComponent();
             Bundle bundle=new Bundle();
             bundle.putString("title",intent.getStringExtra("title"));
             fragment.setArguments(bundle);
             title="Component";
         }
         else if(fragId==CREATE_COMPONENT){
             fragment= new CreateComponent();
             title="Create Component";
         }
         else if(fragId==VIEW_TOWER){
             fragment= new viewTower();
             Bundle bundle=new Bundle();
             bundle.putString("title",intent.getStringExtra("title"));
             fragment.setArguments(bundle);
             title="Tower";
         }
         else if(fragId==CREATE_TOWER){
             fragment= new CreateTower();
             title="Create Tower";
         }


        getSupportFragmentManager().beginTransaction().replace(R.id.childLayout,fragment).commit();
        getSupportActionBar().setTitle(title);
    }

    public void calendar(View view) {
        CalendarView calendarView=(CalendarView)findViewById(R.id.calendar);
        final TextView selectedDate=(TextView)findViewById(R.id.selectedDate);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate.setText(dayOfMonth+"-"+month+"-"+year);
                view.setVisibility(View.GONE);
            }
        });
        if(calendarView.getVisibility()==View.VISIBLE){
            Log.v("v","gone");
            calendarView.setVisibility(View.GONE);
        }
        else{
            Log.v("v","visible");
            calendarView.setVisibility(View.VISIBLE);
        }

    }
}
