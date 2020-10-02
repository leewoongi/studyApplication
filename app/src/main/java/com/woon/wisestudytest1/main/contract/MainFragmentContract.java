package com.woon.wisestudytest1.main.contract;

import com.woon.wisestudytest1.main.presenter.MainPresenter;
import com.woon.wisestudytest1.user.Entity.UserVo;

public interface MainFragmentContract {
    interface fragmentView{
        void initialized();
        void showFragment(UserVo userVo);
    }
}
