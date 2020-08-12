package com.mz.prisonpolice.login;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.muzzz.basic.lifecycle.BaseViewModel;
import com.mz.prisonpolice.callback.IConsumer;
import com.mz.prisonpolice.entity.LoginResultEntity;

import io.reactivex.disposables.Disposable;

/**
 * @author Muzzz
 * @CreateTime 2020/8/12
 * @Desc
 */
public class LoginViewModel extends BaseViewModel {

    private LoginModel mLoginModel = new LoginModel();

    /**
     * 当数据请求成功回调
     */
    protected MutableLiveData<LoginResultEntity> mLoginResultLiveData = new MutableLiveData<>();

    protected MutableLiveData<String> mAccData = new MutableLiveData<>();
    protected MutableLiveData<String> mPswData = new MutableLiveData<>();

    /**
     * 请求网络数据
     */
    public void onLoginClick(View view) {
        mShowDialogData.setValue(true, "加载中");

        Disposable disposable = mLoginModel.login(mAccData.getValue(), mPswData.getValue(), new IConsumer<LoginResultEntity>() {
            @Override
            public void accept(LoginResultEntity entity) {
                // 通知View刷新
                mShowDialogData.setValue(false);
                mLoginResultLiveData.setValue(entity);
            }

            @Override
            public void accept(Throwable throwable) {
                // 通知View刷新
                mShowDialogData.setValue(false);
                // 发生了错误，通知UI层
                mErrorData.setValue("发生错误了");
            }
        });
        addDisposable(disposable);
    }

    public MutableLiveData<LoginResultEntity> getLoginResultLiveData() {
        return mLoginResultLiveData;
    }

    public MutableLiveData<String> getAccData() {
        return mAccData;
    }

    public void setAccData(String accData) {
        mAccData.setValue(accData);
    }

    public MutableLiveData<String> getPswData() {
        return mPswData;
    }

    public void setPswData(String pswData) {
        mPswData.setValue(pswData);
    }
}
