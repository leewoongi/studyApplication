package com.woon.wisestudytest1.main.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.woon.wisestudytest1.main.contract.MainContract;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter implements MainContract.pageAdapterView, MainContract.pageAdapterModel {

    List<Fragment> mainFragment = new ArrayList<>();
    public MainPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mainFragment.get(position);
    }

    @Override
    public int getCount() {
        return mainFragment.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void addFragments(List<Fragment> page) {
        mainFragment = page;
    }
}
