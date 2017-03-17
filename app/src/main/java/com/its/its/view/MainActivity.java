package com.its.its.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.its.its.R;
import com.its.its.model.tasks.LogoutTask;

@SuppressWarnings("ResourceAsColor")
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int txtMoneyId = 5;
    public static TextView tvMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(R.color.background_app);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(MainActivity.this, "Portrait", Toast.LENGTH_SHORT).show();
            callFragment(new MainFragment(), R.id.llContentMainPortrait);
        }
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//            Toast.makeText(MainActivity.this, "Landscape", Toast.LENGTH_SHORT).show();
            callFragment(new MainFragment(), R.id.frame1);
            callFragment(new MainFragment(), R.id.frame2);
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

    @SuppressWarnings("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        tvMoney= new TextView(this);
        tvMoney.setId(txtMoneyId);
        tvMoney.setText("$ 1,234,567");
        tvMoney.setTextColor(Color.YELLOW);
        tvMoney.setPadding(5, 0, 20, 0);

        tvMoney.setTypeface(null, Typeface.BOLD);
        tvMoney.setTextSize(14);
        menu.add(0, txtMoneyId, 1, getResources().getString(R.string.money)).setActionView(tvMoney).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String userID = getIntent().getStringExtra("ID");
        String token = getIntent().getStringExtra("TOKEN");
        int id = item.getItemId();

        if (id == R.id.nav_deposit) {

        } else if (id == R.id.nav_withdraw) {
            Intent intent = new Intent(MainActivity.this, WithdrawActivity.class);
            intent.putExtra("ID", userID);
            intent.putExtra("TOKEN", token);
            startActivity(intent);
        } else if (id == R.id.nav_viewStatistics) {

        } else if (id == R.id.nav_editProfile) {
            Intent intent = new Intent(MainActivity.this, UpdateProfileActivity.class);
            intent.putExtra("ID", userID);
            intent.putExtra("TOKEN", token);
            startActivity(intent);
        } else if(id == R.id.nav_editPassword){
            Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
            intent.putExtra("ID", userID);
            intent.putExtra("TOKEN", token);
            startActivity(intent);
        } else if (id == R.id.nav_logOut) {
            new LogoutTask(MainActivity.this).execute(
                    "http://192.168.100.14:8080/Demo/user/" + getIntent().getStringExtra("ID"),
                    getIntent().getStringExtra("TOKEN"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callFragment(Fragment fragment, int id) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
        transaction.replace(id, fragment);
        transaction.commit();
    }
}
