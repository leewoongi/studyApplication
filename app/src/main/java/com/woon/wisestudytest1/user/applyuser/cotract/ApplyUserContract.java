package com.woon.wisestudytest1.user.applyuser.cotract;

import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.applyuser.view.ApplyUserRecyclerViewAdapter;

import java.util.List;

public interface ApplyUserContract {

    interface view{
        void showApplyInformation();
        void showToast();
    }

    interface presenter{
        void retrieveApply(int study_id);
        void responseApply(List<UserVo> data);

        void confirmApply(int user_id);
        void rejectApply(int user_id);

        void setAdapterView(ApplyUserContract.adapterView view);
        void setAdapterModel(ApplyUserContract.adapterModel model);
    }

    interface adapterView{
        void refresh();
    }

    interface adapterModel{
        void addItems(List<UserVo> data);
    }
}
