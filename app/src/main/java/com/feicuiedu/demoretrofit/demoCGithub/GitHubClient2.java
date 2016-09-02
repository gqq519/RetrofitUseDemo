package com.feicuiedu.demoretrofit.demoCGithub;


import com.feicuiedu.demoretrofit.demoAOkHttp.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubClient2 {

    private static final String API_URL = "https://api.github.com/";

    public static void getContributors(Callback<List<Contributor>> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubApi2 gitHubApi2 = retrofit.create(GitHubApi2.class);

        Call<List<Contributor>> call = gitHubApi2.contributors("square", "retrofit");
        call.enqueue(callback);

    }

}
