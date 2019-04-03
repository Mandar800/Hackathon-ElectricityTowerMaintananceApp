package com.app.shaalastic;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.shaalastic.Assignments.Assignments;
import com.app.shaalastic.Users.Users;
import com.app.shaalastic.data.DBHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHelper dbHelper=new DBHelper(this);
    int currentNavigationItem=R.id.nav_dashboard;
    Menu menu=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor c=db.query(DBHelper.USER_TABLE,null,null,null,null,null,null);
        if(c.getCount()==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Login()).commit();
            getSupportActionBar().hide();
        }

        else{
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setCheckedItem(R.id.nav_dashboard);
            setNavigationItem(R.id.nav_dashboard);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu=menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i=new Intent(this,ChildActivity.class);
        if(id==R.id.menuitem_createAssignment){
            i.putExtra("fragment",ChildActivity.CREATE_ASSIGNMENT);
            startActivity(i);
        }
        else if (id==R.id.menuitem_takeAttendance){
            i.putExtra("fragment",ChildActivity.TAKE_ATTENDANCE);
            startActivity(i);
        }
        else if(id==R.id.menuitem_createUser){
            i.putExtra("fragment",ChildActivity.CREATE_USER);
            startActivity(i);
        }
        else if(id==R.id.menuitem_addparent){
            i.putExtra("fragment",ChildActivity.ADD_PARENT);
            startActivity(i);
        }
        else if (id==R.id.menuitem_addSM){
            i.putExtra("fragment",ChildActivity.VIEW_STUDY_MAT);
            startActivity(i);
        }else if(id==R.id.menuitem_createclass){
            i.putExtra("fragment",ChildActivity.Create_Classroom);
            startActivity(i);
        }else if(id==R.id.menuitem_createclasstt){
            i.putExtra("fragment",ChildActivity.Add_ClassroomTT);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (currentNavigationItem!=id){
            currentNavigationItem=id;
            setNavigationItem(id);
        }
        return true;
    }
    private void setNavigationItem(int id){
        Fragment fragment=null;
        String bar=null;
        int menuId=0;
        if (id == R.id.nav_dashboard) {
            fragment=new Dashboard();
            bar="Dashboard";
        } else if (id == R.id.nav_assignments) {
            fragment=new Assignments();
            bar="Assignments";
            menuId=R.menu.menu_assignments;
        }
        getSupportActionBar().setTitle(bar);
        if (menu!=null){
            setOptionsMenu(menuId);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void login(View view) {

        Spinner institute=(Spinner)findViewById(R.id.selectInstitute);
        if (institute.getSelectedItemPosition()==0){
            Toast.makeText(this,"Please select an institute",Toast.LENGTH_SHORT).show();
            return;
        }
        EditText id=(EditText)findViewById(R.id.usernameText);
        EditText password=(EditText)findViewById(R.id.passwordText);
        if(id.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter credentials",Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DBHelper.COLUMN_ID,id.getText().toString());
        values.put(DBHelper.COLUMN_PASSWORD,password.getText().toString());
        values.put(DBHelper.COLUMN_INSTITUTE,institute.getSelectedItem().toString());
        database.insert(DBHelper.USER_TABLE,null,values);
        recreate();
    }
    public void setOptionsMenu(int menuId){
        menu.clear();
        if(menuId!=0){
            getMenuInflater().inflate(menuId, menu);
        }
    }
}
