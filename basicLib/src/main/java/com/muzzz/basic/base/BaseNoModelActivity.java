package com.muzzz.basic.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.muzzz.basic.utils.ActivityUtil;
import com.muzzz.basic.view.LoadingDialog;

public abstract class BaseNoModelActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    protected DB mDataBinding;
    protected Context mContext;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityUtil.getInstance().addActivity(this);
        int layoutId = onCreate();
        setContentView(layoutId);

        mDataBinding = initDataBinding(layoutId);
        initView();
        initData();
    }

    /**
     * 初始化要加载的布局资源ID
     * 此函数优先执行于onCreate()可以做window操作
     */
    protected abstract int onCreate();


    /**
     * 初始化DataBinding
     */
    protected DB initDataBinding(@LayoutRes int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 显示用户等待框
     *
     * @param msg 提示信息
     */
    protected void showDialog(String msg) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.setLoadingMsg(msg);
        } else {
            mLoadingDialog = new LoadingDialog(mContext);
            mLoadingDialog.setLoadingMsg(msg);
            mLoadingDialog.show();
        }
    }

    /**
     * 隐藏等待框
     */
    protected void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDataBinding != null) {
            mDataBinding.unbind();
        }
        ActivityUtil.getInstance().removeActivity(this);
    }
}
