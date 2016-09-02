package com.feicuiedu.demoretrofit.demoBGithub;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi1 {
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributors(@Path("owner") String owner, @Path("repo") String repo);
}
