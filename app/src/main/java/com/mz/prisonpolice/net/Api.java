package com.mz.prisonpolice.net;

import com.muzzz.basic.retrofit.BaseApi;

import okhttp3.OkHttpClient;

public class Api extends BaseApi {

    /**
     * 静态内部类单例
     */
    private static class ApiHolder {
        private static Api api = new Api();
        private final static ApiService apiService = api.initRetrofit(ApiService.BASE_URL)
                .create(ApiService.class);
        private final static ApiService JueJinApiService = api.initRetrofit(ApiService.JUE_JIN_BASE_URL)
                .create(ApiService.class);
    }

    public static ApiService getInstance() {
        return ApiHolder.apiService;
    }

    public static ApiService getJueJinInstance() {
        return ApiHolder.JueJinApiService;
    }

    /**
     * 做自己需要的操作
     */
    @Override
    protected OkHttpClient setClient() {
        return null;
    }

}
