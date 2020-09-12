package com.woon.wisestudytest1.user.user.contract;

import com.woon.wisestudytest1.user.Entity.UserStudies;

import java.util.List;

public interface UserAdapterContract {
    interface adapterView{
        void refresh();
    }

    interface adapterModel{

        void addItems(List<UserStudies> studies);
    }
}
