package com.woon.wisestudytest1.user.user.contract;

public interface AdapterContract {
    interface view{
        void notifyAdapter();
    }
    interface model{
        void addItems();
        void clearItems();
    }

}
