package com.woon.wisestudytest1.user.detailschedule.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.woon.wisestudytest1.user.detailschedule.contract.DetailSchedulePageAdapterContract;

import java.util.ArrayList;
import java.util.List;

public class DetailSchedulePageAdapter extends FragmentStatePagerAdapter implements DetailSchedulePageAdapterContract.view, DetailSchedulePageAdapterContract.model {

    private List<Fragment> fragments = new ArrayList<>();
    public DetailSchedulePageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void addPage(List<Fragment> list) {
        fragments = list;
    }
}
