package com.woon.wisestudytest1.user.detailschedule.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.detailschedule.contract.DetailScheduleContract;
import com.woon.wisestudytest1.user.detailschedule.presenter.DetailSchedulePresenter;
import com.woon.wisestudytest1.util.UiHelper;

public class DetailScheduleActivity extends AppCompatActivity implements DetailScheduleContract.view, TabLayout.OnTabSelectedListener {

    private DetailScheduleContract.presenter presenter;
    private DetailSchedulePageAdapter pageAdapter;
    private MaterialTextView studyTitle;
    private ShapeableImageView imageView;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schdule);

        UiHelper.toolBarInitialize(this, findViewById(R.id.detailScheduleToolBar));
        UiHelper.hideWindow(this);

        initialized();


        int study_id = getIntent().getIntExtra("studyId",0);
        int schedule_id = getIntent().getIntExtra("scheduleId",0);

        pageAdapter = new DetailSchedulePageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        presenter = new DetailSchedulePresenter(DetailScheduleActivity.this,tabLayout);
        // 스터디 이미지 가져오기
        presenter.retrieveStudyInformation(study_id);

        presenter.setPageAdapterView(pageAdapter);
        presenter.setPageAdapterModel(pageAdapter);

        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        Bundle bundle = new Bundle();
        presenter.loadFragment(bundle,study_id,schedule_id);
    }

    private void initialized() {
        viewPager = findViewById(R.id.detailScheduleViewPager);
        tabLayout = findViewById(R.id.detailScheduleTabLayout);
        imageView = findViewById(R.id.detailScheduleImageView);
        studyTitle = findViewById(R.id.detailScheduleTitle);

        tabLayout.addTab(tabLayout.newTab().setText("일정"));
        tabLayout.addTab(tabLayout.newTab().setText("위치보기"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.push_management, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.manage){

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void showStudyImage(Uri uri, String title) {
        Glide.with(this).load(uri).into(imageView);
        studyTitle.setText(title);
    }
}
