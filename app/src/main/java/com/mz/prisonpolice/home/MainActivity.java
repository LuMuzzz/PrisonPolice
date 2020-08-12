package com.mz.prisonpolice.home;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.muzzz.basic.base.BaseNoModelActivity;
import com.mz.prisonpolice.R;
import com.mz.prisonpolice.databinding.ActivityMainBinding;
import com.mz.prisonpolice.login.LoginActivity;

public class MainActivity extends BaseNoModelActivity<ActivityMainBinding> {


    @Override
    protected int onCreate() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mDataBinding.setModel(this);
    }

    /**
     * 按钮点击事件
     */
    public void item(View view, int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case 1:
                Toast.makeText(mContext, "点击了第二个", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
