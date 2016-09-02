package com.feicuiedu.demoretrofit.demoBGithub;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi1 {

    /**
     * 使用OkHttp进行接口网址的拼接，使用Retrofit
     * @param owner
     * @param repo
     * @return
     */
    /**
     //https://api.github.com/repos/owner/repo/contributors
     //https://api.github.com/repos/{owner}/{repo}/contributors

     */
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributors(@Path("owner") String owner, @Path("repo") String repo);

}
