package com.feicuiedu.demoretrofit.demoCGithub;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.feicuiedu.demoretrofit.R;
import com.feicuiedu.demoretrofit.demoAOkHttp.Contributor;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContributorsActivity2 extends AppCompatActivity{

    @Bind(R.id.listView)
    ListView listView;
    ArrayAdapter<Contributor> adapter;

    private Callback<List<Contributor>> callback = new Callback<List<Contributor>>() {
        @Override
        public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
            adapter.addAll(response.body());
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<List<Contributor>> call, Throwable t) {
            Toast.makeText(ContributorsActivity2.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
        GitHubClient2.getContributors(callback);
    }
}
