package com.mz.prisonpolice.net;


import com.mz.prisonpolice.entity.LoginResultEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {

    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    String JUE_JIN_BASE_URL = "http://timeline-merger-ms.juejin.im/v1/";

    String LOGIN = "login";

    @POST(LOGIN)
    @FormUrlEncoded
    Observable<LoginResultEntity> login(@Field("acc") String acc, @Field("psw") String psw);
}
