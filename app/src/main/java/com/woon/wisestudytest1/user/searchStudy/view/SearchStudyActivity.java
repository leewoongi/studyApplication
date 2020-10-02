package com.woon.wisestudytest1.user.searchStudy.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.searchStudy.contract.SearchStudyContract;
import com.woon.wisestudytest1.user.searchStudy.presenter.SearchStudyPresenter;
import com.woon.wisestudytest1.util.UiHelper;

public class SearchStudyActivity extends AppCompatActivity implements SearchStudyContract.view, AdapterView.OnItemClickListener {

    private SearchStudyPresenter presenter;
    private RecyclerView studySearchRecyclerView;
    private  SearchStudyRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_study_fragment);

        UiHelper.hideWindow(this);
        UiHelper.toolBarInitialize(this, findViewById(R.id.studySearchToolBar));

        initialized();
        recyclerInitialized();
        presenter = new SearchStudyPresenter(SearchStudyActivity.this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);
        presenter.retrieveSearchStudy();

        String[] field = getResources().getStringArray(R.array.favoriteField);
        ArrayAdapter<String> autoCompleteTextAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, field);
        autoCompleteTextView.setOnItemClickListener(this);
        autoCompleteTextView.setAdapter(autoCompleteTextAdapter);

    }
    private void initialized() {
        studySearchRecyclerView = findViewById(R.id.studySearchRecyclerView);
        autoCompleteTextView = findViewById(R.id.studyFieldAuto);
    }

    private void recyclerInitialized() {
        adapter = new SearchStudyRecyclerViewAdapter();
        layoutManager = new GridLayoutManager(this,2);
        studySearchRecyclerView.setLayoutManager(layoutManager);
        studySearchRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectField = ((TextView) view).getText().toString();
        presenter.selectFieldSearchStudy(selectField);
    }
}
