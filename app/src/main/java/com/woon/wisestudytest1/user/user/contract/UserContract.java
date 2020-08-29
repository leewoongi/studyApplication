package com.woon.wisestudytest1.user.user.contract;

public interface UserContract {
    interface view{
        void showInformation();
        void showJoinedStudy();
        void showFavoriteField();
    }

    interface presenter{
        void demandPicture();
        void demandInformation();

    }

    interface remoteModel{

    }

    interface localModel{
        void getPicture();
    }
}
