package com.yashdalfthegray.androidtutorial.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.yashdalfthegray.androidtutorial.Adapters.NavDrawerAdapter;
import com.yashdalfthegray.androidtutorial.Fragments.AttendantsFragment;
import com.yashdalfthegray.androidtutorial.Fragments.CreateEventFragment;
import com.yashdalfthegray.androidtutorial.Fragments.EventListFragment;
import com.yashdalfthegray.androidtutorial.Fragments.RegistrationFragment;
import com.yashdalfthegray.androidtutorial.Fragments.ReportsFragment;
import com.yashdalfthegray.androidtutorial.Fragments.SettingsFragment;
import com.yashdalfthegray.androidtutorial.Models.DrawerItem;
import com.yashdalfthegray.androidtutorial.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String HEADER_NAME = "Yash Kulshrestha";
    public String HEADER_EMAIL = "yash.kulshrestha@gmail.com";
    public int HEADER_IMAGE = R.drawable.yash;

    private final static int ATTENDANCE_FRAGMENT = 1;
    private final static int EVENTS_FRAGMENT = 2;
    private final static int REGISTRATION_FRAGMENT = 3;
    private final static int CREATE_EVENT_FRAGMENT = 4;
    private final static int REPORTS_FRAGMENT = 5;
    private final static int SETTINGS_FRAGMENT = 6;

    private int currentFragment = 1;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout drawer;
    private RecyclerView.Adapter mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<DrawerItem> dataList;

    private void addItemsToDataList(){
        dataList.add(new DrawerItem(getString(R.string.title_attendants), R.drawable.ic_perm_identity_black_36dp));
        dataList.add(new DrawerItem(getString(R.string.title_events), R.drawable.ic_event_black_36dp));
        dataList.add(new DrawerItem(getString(R.string.title_registration), R.drawable.ic_group_add_black_36dp));
        dataList.add(new DrawerItem(getString(R.string.title_create_event), R.drawable.ic_schedule_black_36dp));
        dataList.add(new DrawerItem(getString(R.string.title_reports), R.drawable.ic_assessment_black_36dp));
        dataList.add(new DrawerItem(getString(R.string.title_settings), R.drawable.ic_settings_black_36dp));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        dataList = new ArrayList<DrawerItem>();
        addItemsToDataList();

        mAdapter = new NavDrawerAdapter(dataList, this, HEADER_NAME, HEADER_EMAIL, HEADER_IMAGE);

        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        drawer = (DrawerLayout)findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp (MotionEvent e) {
                return true;
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    drawer.closeDrawers();
                    onTouchDrawer(recyclerView.getChildLayoutPosition(child));
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

        drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        onTouchDrawer(currentFragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openFragment (final Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void onTouchDrawer (final int position) {
        currentFragment = position;
        switch (position) {
            case ATTENDANCE_FRAGMENT:
                openFragment(new AttendantsFragment());
                setTitle(R.string.title_attendants);
                break;
            case EVENTS_FRAGMENT:
                openFragment(new EventListFragment());
                setTitle(R.string.title_events);
                break;
            case REGISTRATION_FRAGMENT:
                openFragment(new RegistrationFragment());
                setTitle(R.string.title_registration);
                break;
            case CREATE_EVENT_FRAGMENT:
                openFragment(new CreateEventFragment());
                setTitle(R.string.title_create_event);
                break;
            case REPORTS_FRAGMENT:
                openFragment(new ReportsFragment());
                setTitle(R.string.title_reports);
                break;
            case SETTINGS_FRAGMENT:
                openFragment(new SettingsFragment());
                setTitle(R.string.title_settings);
            default:
                break;
        }
    }
}
