package com.woon.wisestudytest1.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.createstudy.view.CreateStudyActivity;
import com.woon.wisestudytest1.user.schedule.view.ScheduleActivity;
import com.woon.wisestudytest1.user.searchStudy.view.SearchStudyActivity;
import com.woon.wisestudytest1.user.user.view.UserActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    /**
     * 바텀 네비게이션
     * @param activity
     * @param view
     */
    public static void navigationOnclick(AppCompatActivity  activity, View view){

        BottomNavigationView bottomNavigationView = (BottomNavigationView) view;
        Context context = activity.getApplicationContext();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Class clazz = null;

                switch (item.getItemId()){
                    case R.id.action_create :
                        clazz = CreateStudyActivity.class;
                        break;

                    case R.id.action_schedule :
                        clazz = ScheduleActivity.class;
                        break;

                    case R.id.action_search :
                        clazz = SearchStudyActivity.class;
                        break;

                    case R.id.action_my :
                        clazz = UserActivity.class;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }

                Intent intent = new Intent(context, clazz);
                activity.startActivity(intent);

                return true;
            }
        });
    }
}
