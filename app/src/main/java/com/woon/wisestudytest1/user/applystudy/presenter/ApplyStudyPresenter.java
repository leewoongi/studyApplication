package com.woon.wisestudytest1.user.applystudy.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;

import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.applystudy.contract.ApplyStudyContract;
import com.woon.wisestudytest1.user.applystudy.model.RemoteApplyStudyModel;
import com.woon.wisestudytest1.user.applystudy.view.ApplyStudyActivity;

public class ApplyStudyPresenter implements ApplyStudyContract.presenter {

    private ApplyStudyActivity view;
    private RemoteApplyStudyModel remoteApplyStudyModel;

    public ApplyStudyPresenter(ApplyStudyActivity applyStudyActivity) {
        this.view = applyStudyActivity;
        remoteApplyStudyModel = new RemoteApplyStudyModel(this);
    }

    @Override
    public void retrieveStudyInformation(int studyId) {
        remoteApplyStudyModel.getApplyStudyInformation(studyId);
    }

    @Override
    public void responseStudyInformation(DetailStudyVo detailStudyVo) {
        Uri uri = Uri.parse(detailStudyVo.getStudy_image());
        String studyName = detailStudyVo.getTitle();
        String description = detailStudyVo.getDescription();
        int needMember = detailStudyVo.getLimit();

        view.showInformation(uri, studyName, description, needMember);
    }

    @Override
    public void responseApplyMember() {
        view.nextActivity();
    }

    public void dialogStart(Context context, String message, int studyId, String userKey) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        // 확인버튼
        alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remoteApplyStudyModel.postApplyMemberInStudy(studyId, userKey);
            }
        });

        // 취소버튼
        alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // 메인 다이얼로그 생성
        AlertDialog alert = alertDialog.create();
        // 다이얼로그 보기
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });

        alert.show();
    }

}
