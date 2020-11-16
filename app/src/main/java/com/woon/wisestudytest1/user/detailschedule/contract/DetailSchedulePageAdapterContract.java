package com.woon.wisestudytest1.user.detailschedule.contract;

import androidx.fragment.app.Fragment;

import java.util.List;

public interface DetailSchedulePageAdapterContract {

    interface view{
        void refresh();
    }

    interface model{
        void addPage(List<Fragment> list);
    }
}
