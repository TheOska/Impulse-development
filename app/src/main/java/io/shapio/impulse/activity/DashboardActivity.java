package io.shapio.impulse.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.shapio.impulse.R;
import io.shapio.impulse.adapter.IllHistoryAdapter;
import io.shapio.impulse.app.EndPoints;
import io.shapio.impulse.app.MyApplication;
import io.shapio.impulse.model.IllHistoryItem;

/**
 * Created by TheOSka on 18/4/2016.
 */
public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String TAG = DashboardActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView illHistoryRecyclerView;
    private ArrayList<IllHistoryItem> arrayListIllHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initToolbar();
        initDrawer();
//        initRecyclerView();

        initChart();
    }

    private void initRecyclerView() {
        arrayListIllHistory = new ArrayList<>();
        fetchIllHistory();
        mAdapter = new IllHistoryAdapter(this, arrayListIllHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        illHistoryRecyclerView = (RecyclerView) findViewById(R.id.ill_history_recycler_view);
        illHistoryRecyclerView.setLayoutManager(layoutManager);
//        illHistoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        illHistoryRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

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

    private void initChart() {
        LineChart chart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();


        float[] steps = {4688.000f, 5291.000f, 5291.000f, 14190.000f, 3770.000f, 12577.000f, 8707.000f, 10853.000f, 6705.000f,
                6872.000f, 7907.000f, 8238.000f, 7039.000f, 6986.000f, 12023.000f, 3123.000f, 7391.000f};

        for (int i = 0; i < 14; i++) {
            Entry temp = new Entry(steps[i], i);
            valsComp1.add(temp);
        }
        //...
        // use the interface ILineDataSet
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        LineDataSet setComp1 = new LineDataSet(valsComp1, "My Steps");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setCircleRadius(10);
        setComp1.setLineWidth(5);
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");


        LineData data = new LineData(xVals, dataSets);
        chart.setData(data);
        chart.invalidate(); // refresh
        chart.setDescription("My Steps over 2 weeks");
        chart.setFocusable(true);
        chart.setFocusableInTouchMode(true);
        chart.notifyDataSetChanged();
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
                Log.v("oskackh", "response: " + response);
                try {
                    JSONObject obj = new JSONObject(response);

                    // check for error flag
                    if (obj.getBoolean("error") == false) {
                        JSONArray illHistoryJsonArray = obj.getJSONArray("ill_history");
                        Log.v("oskackh", "in fetch response");
                        for (int i = 0; i < illHistoryJsonArray.length(); i++) {
                            JSONObject illHistoryObj = (JSONObject) illHistoryJsonArray.get(i);
                            IllHistoryItem illHistoryItem = new IllHistoryItem();
                            Log.v("kkkh", illHistoryObj.getString("disease_name"));
                            illHistoryItem.setDiseaseName(illHistoryObj.getString("disease_name"));
                            illHistoryItem.setDate(illHistoryObj.getString("create_date"));
                            illHistoryItem.setIllID(illHistoryObj.getString("history_id"));
//                            illHistoryItem.setDiseaseDesc(illHistoryObj.getString("disease_desc"));

                            arrayListIllHistory.add(illHistoryItem);
                            Log.v("oskackh", "end");

                        }

                    } else {
                        // error in fetching chat rooms
                        Log.v("oskackh", "error");

                        Toast.makeText(getApplicationContext(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e("oskackh", "json parsing error: " + e.getMessage());
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
                Log.e("oskackh", "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getApplicationContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
    }
}