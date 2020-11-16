package com.woon.wisestudytest1.main.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.landing.view.LandingActivity;
import com.woon.wisestudytest1.login.view.LoginActivity;
import com.woon.wisestudytest1.main.contract.MainContract;
import com.woon.wisestudytest1.main.presenter.MainPresenter;
import com.woon.wisestudytest1.util.UiHelper;

public class MainActivity extends AppCompatActivity implements MainContract.view, TabLayout.OnTabSelectedListener {

    private MainContract.presenter presenter;
    private MainPagerAdapter adapter;

    private ViewPager mainViewPager;
    private TabLayout mainTabLayout;

    private String userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialized();
        tabInitialized();

        int result = getIntent().getIntExtra("RESULT",0);

        presenter = new MainPresenter(MainActivity.this);
        adapter = new MainPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        presenter.setViewPagerView(adapter);
        presenter.setViewPagerModel(adapter);

        mainViewPager.setAdapter(adapter);
        mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTabLayout));

        Bundle bundle = new Bundle();
        if(result == 1){
            userKey = LandingActivity.userKey;
        }else{
            userKey = LoginActivity.userKey;
        }

        presenter.loadFragment(bundle, userKey);
    }

    private void initialized() {

        UiHelper.hideWindow(this);
        mainViewPager = findViewById(R.id.mainViewPager);
        mainTabLayout = findViewById(R.id.mainTabLayout);
    }

    private void tabInitialized() {
        mainTabLayout.addTab(mainTabLayout.newTab().setText("마이페이지"));
        mainTabLayout.addTab(mainTabLayout.newTab().setText("스터디 생성"));
        mainTabLayout.addTab(mainTabLayout.newTab().setText("스케줄"));
        mainTabLayout.addTab(mainTabLayout.newTab().setText("스터디 찾기"));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mainViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}