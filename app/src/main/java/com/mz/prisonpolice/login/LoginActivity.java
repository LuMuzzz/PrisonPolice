package com.mz.prisonpolice.login;

import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.muzzz.basic.base.BaseActivity;
import com.mz.prisonpolice.R;
import com.mz.prisonpolice.databinding.ActivityLoginBinding;
import com.mz.prisonpolice.entity.LoginResultEntity;

public class LoginActivity extends BaseActivity<LoginViewModel, ActivityLoginBinding> {

    @Override
    protected int onCreate() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mDataBinding.setModel(mViewModel);
        mDataBinding.setLifecycleOwner(this);

        // 监听登录状态数据变化，决定页面展示逻辑
        mViewModel.getLoginResultLiveData().observe(this, new Observer<LoginResultEntity>() {
            @Override
            public void onChanged(LoginResultEntity loginResultEntity) {
                if (loginResultEntity != null && loginResultEntity.isSuccess()) {
                    Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected LoginViewModel initViewModel() {
        return ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    protected void showError(Object obj) {
        Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
    }
}