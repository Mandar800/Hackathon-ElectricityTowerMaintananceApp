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
import com.app.shaalastic.Users.CreateUser;

public class ChildActivity extends AppCompatActivity {

    public static int TAKE_ATTENDANCE=100;
    public static int CREATE_ASSIGNMENT=200;
    public static int VIEW_ASSIGNMENT=201;
    public static int CREATE_USER=300;
    public static int VIEW_USER=301;
    public static int ADD_PARENT=400;
    public static int VIEW_STUDY_MAT=202;
    public static int Create_Classroom=203;
    public static int Add_ClassroomTT=204;
    public static int View_classrooms=231;
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
