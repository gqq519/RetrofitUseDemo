package com.feicuiedu.demoretrofit.demoCGithub;

import com.feicuiedu.demoretrofit.demoAOkHttp.Contributor;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GitHubApi2 {

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);


    /**
     * 注解的使用
     * <p>
     * 1.GET，POST，PUT。。。。@GET("网址")，@POST("网址")   表明他的一个请求方式
     * 2.http://www.baidu.com/day/images?q=123&order=desc&aa=asda&adsa=dsds;
     */
    //http://www.baidu.com/day/年/月/日/images
    @GET("http://www.baidu.com/day/{year}/{month}/{day}/images")
    Call<ResponseBody> getImages(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    //http://www.baidu.com/day/images?q=123&order=desc&aa=asda&adsa=dsds

    @GET("http://www.baidu.com/day/images")
    Call<ResponseBody> getImage(@Query("q") String q, @Query("order") String order);

    @GET("http://www.baidu.com/day/images")
    Call<ResponseBody> getImage(@QueryMap Map<String, String> po);

    @PUT("http://www")
    @Headers("Access-type:application/json")
    Call<ResponseBody> getMult(@Part("photo") RequestBody body, @Part("de") RequestBody body1);

    @PUT("网址")
    @Headers({"Access-type:...", "X-type"})
    Call<ResponseBody> getMult1(@Part("photo") RequestBody body, @Part("de") RequestBody body1);

}
