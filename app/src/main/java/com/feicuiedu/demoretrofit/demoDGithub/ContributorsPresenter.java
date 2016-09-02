package com.feicuiedu.demoretrofit.demoDGithub;


import com.feicuiedu.demoretrofit.demoAOkHttp.Contributor;
import com.feicuiedu.demoretrofit.demoCGithub.GitHubApi2;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContributorsPresenter extends MvpNullObjectBasePresenter<ContributorsView>{
    private static final String API_URL = "https://api.github.com/";

    private GitHubApi2 gitHubApi2;

    private Call<List<Contributor>> contributorsCall;

    private Callback<List<Contributor>> contributorsCallback  = new Callback<List<Contributor>>() {
        @Override public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
            getView().setData(response.body());
            getView().showContent();
        }

        @Override public void onFailure(Call<List<Contributor>> call, Throwable t) {
            getView().showError(t, pullToRefresh);
        }
    };

    private boolean pullToRefresh;

    public ContributorsPresenter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubApi2 = retrofit.create(GitHubApi2.class);
    }

    public void loadContributors(final boolean pullToRefresh){

        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);

        if (contributorsCall != null) contributorsCall.cancel();
        contributorsCall = gitHubApi2.contributors("square", "retrofit");
        contributorsCall.enqueue(contributorsCallback);
    }

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);

        if (!retainInstance && contributorsCall != null) {
            contributorsCall.cancel();
            contributorsCall = null;
        }
    }
}
