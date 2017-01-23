package app.custom.com.customapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Handler;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import app.custom.com.customapp.R;
import app.custom.com.customapp.SlidingImage_Adapter;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class WelcomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        /*HomeFragment.OnFragmentInteractionListener,
        PhotoUploadFragment.OnFragmentInteractionListener,
        GalleryFragment.OnFragmentInteractionListener,
        GpsTrackingFragment.OnFragmentInteractionListener*/ {
    boolean doubleBackToExitPressedOnce = false;
    private  ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.customs1, R.drawable.customs2, R.drawable.custom3, R.drawable.customs4, R.drawable.customs5};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();


    DrawerLayout drawerLayout;
    // CirclePageIndicator Indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Activity");

        //LinearLayout container = (LinearLayout) findViewById(R.id.container);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        init();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

   /* @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcom, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        drawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.photo_upload:
                Intent intent = new Intent(WelcomActivity.this,MultipleSelectImageActivity.class);
                startActivity(intent);
                return true;
            case R.id.home:
               //Toast.makeText(getApplicationContext(),"Leave Selected",Toast.LENGTH_SHORT).show();
                /*HomeFragment homeFragment = new HomeFragment();
                android.support.v4.app.FragmentTransaction homeFragmentTransaction = getSupportFragmentManager().beginTransaction();
                homeFragmentTransaction.replace(R.id.container, homeFragment);
                homeFragmentTransaction.commit();*/
                return true;
                //Intent intete = new Intent();
            case R.id.gallery:
                Intent intent1 = new Intent(WelcomActivity.this, GalleryActivity.class);
                startActivity(intent1);
                return true;
            case R.id.gps_tracking:
//                Toast.makeText(getApplicationContext(),"Leave Selected",Toast.LENGTH_SHORT).show();
                /*GpsTrackingFragment gpsTrackingFragment = new GpsTrackingFragment();
                android.support.v4.app.FragmentTransaction gpsFragmentTransaction = getSupportFragmentManager().beginTransaction();
                gpsFragmentTransaction.replace(R.id.container, gpsTrackingFragment);
                gpsFragmentTransaction.commit();
                return true;*/
        }
        return true;
//        int id = item.getItemId();
//        Fragment fragment = null;
//        Class fragmentClass = null;
//        if (id == R.id.home) {
//            fragmentClass = HomeFragment.class;
//        } else if (id == R.id.photo_upload) {
//            Intent intent = new Intent(WelcomActivity.this,MultipleSelectImageActivity.class);
//            startActivity(intent);
//            //fragmentClass = PhotoUploadFragment.class;
//        } else if (id == R.id.gallery) {
//            fragmentClass = GalleryFragment.class;
//        } else if (id == R.id.cctv) {
//        } else if (id == R.id.gps_tracking) {
//            fragmentClass = GpsTrackingFragment.class;
//        }
//
//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, fragment).commit();
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
    }


    private void init() {
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.sliding);


        mPager.setAdapter(new SlidingImage_Adapter(WelcomActivity.this, ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });



    }
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            finish();
            super.onBackPressed();
            return;
        }
        else{
            this.doubleBackToExitPressedOnce = true;
        }
        new AlertDialog.Builder(this).setIcon(R.drawable.alerticon).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                }).setNegativeButton("No", null).show();
        /*Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        if(doubleBackToExitPressedOnce){
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            },2000);*/
        }

        /*new AlertDialog.Builder(this).setIcon(R.drawable.alerticon).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                }).setNegativeButton("No", null).show();*/
    }


