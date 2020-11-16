package com.woon.wisestudytest1.user.searchStudy.presenter;

import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.searchStudy.contract.SearchStudyContract;
import com.woon.wisestudytest1.user.searchStudy.model.RemoteSearchStudyModel;
import com.woon.wisestudytest1.user.searchStudy.view.SearchStudyActivity;
import com.woon.wisestudytest1.user.searchStudy.view.SearchStudyRecyclerViewAdapter;

import java.util.List;

public class SearchStudyPresenter implements SearchStudyContract.presenter {

    private SearchStudyActivity view;
    private RemoteSearchStudyModel remoteSearchStudyModel;
    private SearchStudyContract.adapterModel adapterModel;
    private SearchStudyContract.adapterView adapterView;

    public SearchStudyPresenter(SearchStudyActivity searchStudyActivity) {
        this.view = searchStudyActivity;
        remoteSearchStudyModel = new RemoteSearchStudyModel(this);
    }

    @Override
    public void retrieveSearchStudy() {
        remoteSearchStudyModel.getSearchStudy();
    }

    @Override
    public void responseSearchStudy(List<DetailStudyVo> data) {
        adapterModel.addItems(data);
        adapterView.changeItems();
    }

    @Override
    public void selectFieldSearchStudy(String field) {
        remoteSearchStudyModel.getSpecificSearchStudy(field);
    }

    @Override
    public void responseSelectFieldStudy(List<DetailStudyVo> data) {
        adapterModel.addItems(data);
        adapterView.changeItems();
    }

    @Override
    public void setAdapterView(SearchStudyRecyclerViewAdapter adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setAdapterModel(SearchStudyRecyclerViewAdapter adapterModel) {
        this.adapterModel = adapterModel;
    }
}
