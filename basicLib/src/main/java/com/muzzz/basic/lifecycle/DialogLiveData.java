package com.muzzz.basic.lifecycle;

import androidx.lifecycle.MutableLiveData;

import com.muzzz.basic.bean.DialogBean;

public final class DialogLiveData<T> extends MutableLiveData<T> {

    private DialogBean mBean = new DialogBean();

    public void setValue(boolean isShow) {
        mBean.setShow(isShow);
        mBean.setMsg("");
        setValue((T) mBean);
    }

    public void setValue(boolean isShow, String msg) {
        mBean.setShow(isShow);
        mBean.setMsg(msg);
        setValue((T) mBean);
    }
}