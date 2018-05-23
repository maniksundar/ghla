package com.ghla.library.authority;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity implements ReportsFragment.OnListFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, LibraryFragment.OnListFragmentInteractionListener {


    private HomeFragment m_homeFragment;
    private ReportsFragment m_reportsFragment;
    private ProfileFragment m_profileFragment;
    private LibraryFragment m_libraryFragment;
    private Fragment m_currentFragment;

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

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        m_homeFragment = new HomeFragment();
        m_reportsFragment = new ReportsFragment();
        m_profileFragment = new ProfileFragment();
        m_libraryFragment = new LibraryFragment();

        getSupportActionBar().setTitle(User.getCurrentUser().getName());

        switchToFragment(m_homeFragment);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(Library library) {

    }

    public void switchToFragment(Fragment fragment) {
        fragment = getTheCorrectFragment(fragment);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.baseFrame, fragment).commit();
    }

    public Fragment getTheCorrectFragment (Fragment fragment){
        if (fragment != m_currentFragment && fragment instanceof TopMost){
            m_currentFragment = fragment;
            return ((TopMost) fragment).getTopMostFragment();
        } else if (fragment == m_currentFragment && fragment instanceof TopMost){
            ((TopMost)fragment).clearAll();
        }
        m_currentFragment = fragment;
        return m_currentFragment;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
