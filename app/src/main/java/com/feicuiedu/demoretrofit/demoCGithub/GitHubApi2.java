package com.feicuiedu.demoretrofit.demoCGithub;

import com.feicuiedu.demoretrofit.demoAOkHttp.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi2 {
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);
}
