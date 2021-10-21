package vn.nh.mrlongg.tracnghiemkhachquan;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.io.IOException;

import vn.nh.mrlongg.tracnghiemkhachquan.info.LienheFragment;
import vn.nh.mrlongg.tracnghiemkhachquan.info.VesAPPFragment;
import vn.nh.mrlongg.tracnghiemkhachquan.monhoc.GDCDFragment;
import vn.nh.mrlongg.tracnghiemkhachquan.monhoc.HomeFragment;
import vn.nh.mrlongg.tracnghiemkhachquan.monhoc.TinHocFragment;
import vn.nh.mrlongg.tracnghiemkhachquan.monhoc.LichSuFragment;
import vn.nh.mrlongg.tracnghiemkhachquan.question.DBHelper;
import vn.nh.mrlongg.tracnghiemkhachquan.score.ScoreFragment;

//import vn..mrlongg.tracnghiem2rt vn.nh.info.LienheFragment;

/**
 * Nguyễn Hoàng Long.
 * Facebook: facebook.com/87269
 * MrLongg
 * 15/07/2017
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homecFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.content_main, homecFragment, homecFragment.getTag()).commit();
        DBHelper db = new DBHelper(this);

//                try {
//            db.deleteDataBase();
//            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "bi loi rui", Toast.LENGTH_SHORT).show();
//        }
        try {
            db.createDataBase();
//            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id){
            case R.id.home:
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
                break;
            case R.id.monlichsu:
                LichSuFragment startFragment = new LichSuFragment();
                fragmentTransaction.replace(R.id.content_main, startFragment, startFragment.getTag()).commit();

                break;
            case R.id.montinhoc:
                TinHocFragment tinHocFragment = new TinHocFragment();
                fragmentTransaction.replace(R.id.content_main, tinHocFragment, tinHocFragment.getTag()).commit();

                break;
            case R.id.mongdcd:
                GDCDFragment gdcdFragment = new GDCDFragment();
                fragmentTransaction.replace(R.id.content_main, gdcdFragment, gdcdFragment.getTag()).commit();

                break;
            case R.id.list_score:
                ScoreFragment scoreFragment = new ScoreFragment();
                fragmentTransaction.replace(R.id.content_main,scoreFragment, scoreFragment.getTag()).commit();
                break;
            case R.id.infotacgia:
                LienheFragment lienheFragment = new LienheFragment();
                fragmentTransaction.replace(R.id.content_main, lienheFragment, lienheFragment.getTag()).commit();
                break;
            case R.id.infoungdung:
                VesAPPFragment vesAPPFragment = new VesAPPFragment();
                fragmentTransaction.replace(R.id.content_main, vesAPPFragment, vesAPPFragment.getTag()).commit();



                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
