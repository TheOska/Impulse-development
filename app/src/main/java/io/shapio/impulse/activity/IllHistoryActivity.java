package io.shapio.impulse.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.shapio.impulse.R;
import io.shapio.impulse.adapter.ChatRoomThreadAdapter;
import io.shapio.impulse.adapter.IllHistoryAdapter;
import io.shapio.impulse.app.EndPoints;
import io.shapio.impulse.app.MyApplication;
import io.shapio.impulse.model.HomePageItem;
import io.shapio.impulse.model.IllHistoryItem;

/**
 * Created by TheOSka on 18/4/2016.
 */
public class IllHistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String TAG = IllHistoryActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView illHistoryRecyclerView;
    private ArrayList<IllHistoryItem> arrayListIllHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ill_history);
        initToolbar();
        initDrawer();
        initRecyclerView();
    }

    private void initRecyclerView() {
        illHistoryRecyclerView = (RecyclerView) findViewById(R.id.ill_history_recycler_view);
        arrayListIllHistory = new ArrayList<>();
        mAdapter = new IllHistoryAdapter(this, arrayListIllHistory);
        fetchIllHistory();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        illHistoryRecyclerView.setLayoutManager(layoutManager);
        illHistoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        illHistoryRecyclerView.setAdapter(mAdapter);
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

    private void fetchIllHistory() {
        StringRequest strReq = new StringRequest(Request.Method.GET,
                EndPoints.ALL_ILL_HISTORY_MESSAGE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response: " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    // check for error flag
                    if (obj.getBoolean("error") == false) {
                        JSONArray illHistoryJsonArray = obj.getJSONArray("ill_history");
                        Log.v("21.4", illHistoryJsonArray.toString());
                        for (int i = 0; i < illHistoryJsonArray.length(); i++) {
                            JSONObject illHistoryObj = (JSONObject) illHistoryJsonArray.get(i);
                            IllHistoryItem illHistoryItem = new IllHistoryItem();

                            illHistoryItem.setDiseaseName(illHistoryObj.getString("disease_name"));
                            illHistoryItem.setDate(illHistoryObj.getString("create_date"));
                            illHistoryItem.setDiseaseDesc(illHistoryObj.getString(" "));

                            arrayListIllHistory.add(illHistoryItem);
                        }

                    } else {
                        // error in fetching chat rooms
                        Toast.makeText(getApplicationContext(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                mAdapter.notifyDataSetChanged();

                // subscribing to all chat room topics
//                subscribeToAllTopics();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getApplicationContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
    }
}