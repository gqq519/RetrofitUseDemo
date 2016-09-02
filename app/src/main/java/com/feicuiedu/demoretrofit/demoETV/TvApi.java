package com.feicuiedu.demoretrofit.demoETV;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TvApi {

    String BASE_URL = "http://japi.juhe.cn/tv/";

    String KEY = "ba894935fac0ed489d99a7f3787210a7";

    @FormUrlEncoded
    @POST("getCategory")
    Call<TvRspBody<TvCategory>> getCategory(@Field("key") String key);

    @FormUrlEncoded
    @POST("getChannel")
    Call<TvRspBody<TvChannel>> getChannel(@Field("key") String key, @Field("pId") int id);

    @FormUrlEncoded
    @POST("getProgram")
    Call<TvRspBody<TvProgram>> getProgram(@Field("key") String key, @Field("code") String code, @Field("date") String date);

}
