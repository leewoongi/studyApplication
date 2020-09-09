package com.woon.wisestudytest1.util;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UiHelper {

    /**
     * 앱바를 사용하려면 activity 마다 같은 코드를 반복적으로 사용해야함
     * 그래서 UIhelper로 빼서 관리해서 실행시 불러서 사용하게 만든 부분
     **/
    public static void toolBarInitialize(AppCompatActivity activity, View view) {
        Toolbar toolbar = (Toolbar) view;
        activity.setSupportActionBar((toolbar));
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        actionBar.setDisplayShowTitleEnabled(false); // 제목 없애기
    }

    /**
     * 앱바 위에 보면 상태 표시줄이 있는데 상태표시줄을 없애는 코드
     **/
    public static void hideWindow(AppCompatActivity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
