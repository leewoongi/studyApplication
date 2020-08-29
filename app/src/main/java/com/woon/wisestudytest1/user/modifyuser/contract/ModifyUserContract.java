package com.woon.wisestudytest1.user.modifyuser.contract;

public interface ModifyUserContract {
    interface view{
        void showPicture();
    }

    interface presenter{
        void requestModifyUser();
    }

    interface remoteModel{
        void putUserInformation();
    }

    interface localModel{
        void getPicture();
    }

}
