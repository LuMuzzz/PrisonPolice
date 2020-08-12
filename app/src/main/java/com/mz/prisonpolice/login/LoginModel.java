package com.mz.prisonpolice.login;

import com.mz.prisonpolice.callback.IConsumer;
import com.mz.prisonpolice.entity.LoginResultEntity;
import com.mz.prisonpolice.net.Api;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Muzzz
 * @CreateTime 2020/8/12
 * @Desc
 */
public class LoginModel {

    public Disposable login(String acc, String psw, final IConsumer<LoginResultEntity> iConsumer) {
        return Api.getInstance().login(acc, psw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResultEntity>() {
                    @Override
                    public void accept(LoginResultEntity entity) throws Exception {
                        iConsumer.accept(entity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iConsumer.accept(throwable);
                    }
                });
    }

}
