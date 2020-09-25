package com.woon.wisestudytest1.user.searchStudy.contract;

import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.searchStudy.view.SearchStudyRecyclerViewAdapter;

import java.util.List;

public interface SearchStudyContract {
    interface view{

    }
    interface presenter{
        void retrieveSearchStudy();
        void responseSearchStudy(List<DetailStudyVo> data);

        void selectFieldSearchStudy(String field);
        void responseSelectFieldStudy(List<DetailStudyVo> data);

        void setAdapterView(SearchStudyRecyclerViewAdapter adapterView);
        void setAdapterModel(SearchStudyRecyclerViewAdapter adapterModel);

    }

    interface adapterView{
        void changeItems();
    }

    interface adapterModel{
        void addItems(List<DetailStudyVo> data);
    }
}
