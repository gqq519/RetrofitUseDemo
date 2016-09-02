package com.feicuiedu.demoretrofit.demoBGithub;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class GitHubClient1 {

    private static final String API_URL = "https://api.github.com/";

    public static void getContributors(Callback<ResponseBody> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();

        GitHubApi1 gitHubApi1 = retrofit.create(GitHubApi1.class);

        Call<ResponseBody> call = gitHubApi1.contributors("square", "retrofit");
        call.enqueue(callback);

    }
}
