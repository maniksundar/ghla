package com.ghla.library.authority;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity implements ReportsFragment.OnListFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener {


    private HomeFragment m_homeFragment;
    private ReportsFragment m_reportsFragment;
    private ProfileFragment m_profileFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchToFragment(m_homeFragment);
                    return true;
                case R.id.navigation_report:
                    switchToFragment(m_reportsFragment);
                    return true;
                case R.id.navigation_profile:
                    switchToFragment(m_profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        m_homeFragment = new HomeFragment();
        m_reportsFragment = new ReportsFragment();
        m_profileFragment = new ProfileFragment();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void switchToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.baseFrame, fragment).commit();
    }
}
