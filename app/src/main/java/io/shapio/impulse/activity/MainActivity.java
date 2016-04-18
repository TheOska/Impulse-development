package io.shapio.impulse.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.shapio.impulse.R;
import io.shapio.impulse.adapter.ChatRoomsAdapter;
import io.shapio.impulse.adapter.HomePageGridAdapter;
import io.shapio.impulse.app.Config;
import io.shapio.impulse.app.EndPoints;
import io.shapio.impulse.app.MyApplication;
//import info.androidhive.gcm.gcm.GcmIntentService;
//import info.androidhive.gcm.gcm.NotificationUtils;
import io.shapio.impulse.helper.SimpleDividerItemDecoration;
import io.shapio.impulse.model.ChatRoom;
import io.shapio.impulse.model.HomePageItem;
import io.shapio.impulse.model.Message;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private RecyclerView homePageRecyclerView;
    private ArrayList<HomePageItem> arrayListHomePageItem;

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initDrawer();
        initHomePageItem();
        initRecyclerView();
    }

    private void initRecyclerView() {
        homePageRecyclerView = (RecyclerView) findViewById(R.id.grid_type_recycler);
        mAdapter = new HomePageGridAdapter(this, arrayListHomePageItem);
        mLayoutManager = new GridLayoutManager(this.getApplication(), 2);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        homePageRecyclerView.setLayoutManager(mLayoutManager);
        homePageRecyclerView.setAdapter(mAdapter);

        homePageRecyclerView.setHasFixedSize(true);
    }

    private void initHomePageItem() {
        String[] iconName = {
                getResources().getString(R.string.item_name_chat),
                getResources().getString(R.string.item_drugs_remind),
                getResources().getString(R.string.item_ill_history),
                getResources().getString(R.string.item_my_profolio),
                getResources().getString(R.string.item_dotoc_paper),
                getResources().getString(R.string.item_setting)
        };
        Integer[] icon = {R.drawable.bg_circle,
                R.drawable.bg_circle,
                R.drawable.bg_circle,
                R.drawable.bg_circle,
                R.drawable.bg_circle,
                R.drawable.bg_circle};
        arrayListHomePageItem = new ArrayList<HomePageItem>();
        for (int i = 0; i < 6; i++) {
            HomePageItem homePageItem = new HomePageItem();
            homePageItem.setItemIcon(icon[i]);
            homePageItem.setItemName(iconName[i]);
            arrayListHomePageItem.add(i, homePageItem);
        }
    }

    private void initDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        MenuInflater inflater = getMenuInflater();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_logout:
                MyApplication.getInstance().logout();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
