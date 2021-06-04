package com.example.vqclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class departmenthome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departmenthome);

        loadingScreen.startloading(departmenthome.this,"loading");    //LOADING SCREEN STARTS

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

//        Bundle extras;
//        if (savedInstanceState == null) {
//            extras = getIntent().getExtras();
//            if (extras == null) {
//                uname = null;
//            } else {
//                uname = extras.getString("uname");
//                thisdepartment = (com.example.vqclientapp.department) getIntent().getSerializableExtra("passdep");
//            }
//        }
//        SaveId.setDepID(this, thisdepartment.getName());

        Toolbar toolbar = findViewById(R.id.depthometoolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.dept_drawer_layout);
        NavigationView navigationView = findViewById(R.id.dept_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        TextView drawerCompanyName = headerview.findViewById(R.id.drawer_comp_name);
        drawerCompanyName.setText(SaveId.getDepID(departmenthome.this).toUpperCase());
        // HAVE TO CHANGE TO DEPT NAME CURRENTLY DEPT ID


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new homeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new homeFragment()).commit();

                break;
            case R.id.nav_tokenlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new tokenlistFragment()).commit();
                break;
            case R.id.nav_qrgenerator:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new QRFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static void stopDeptHomeLoadingScreen(){
        loadingScreen.stoploading();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

}