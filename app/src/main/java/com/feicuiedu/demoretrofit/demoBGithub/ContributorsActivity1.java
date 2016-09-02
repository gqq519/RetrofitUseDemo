package com.feicuiedu.demoretrofit.demoBGithub;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.feicuiedu.demoretrofit.R;
import com.feicuiedu.demoretrofit.demoAOkHttp.Contributor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContributorsActivity1 extends AppCompatActivity{

    @Bind(R.id.listView)
    ListView listView;
    ArrayAdapter<Contributor> adapter;

    private Callback<ResponseBody> callback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            try {
                String body = response.body().string();
                Gson gson = new Gson();
                List<Contributor> contributorList = gson.fromJson(body, new TypeToken<List<Contributor>>(){}.getType());
                adapter.addAll(contributorList);
                adapter.notifyDataSetChanged();
            } catch (IOException e) {
                Toast.makeText(ContributorsActivity1.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(ContributorsActivity1.this, t.getMessage(), Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        GitHubClient1.getContributors(callback);

    }
}
