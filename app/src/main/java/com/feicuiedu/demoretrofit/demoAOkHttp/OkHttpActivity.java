package com.feicuiedu.demoretrofit.demoAOkHttp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.feicuiedu.demoretrofit.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpActivity extends AppCompatActivity{

    @Bind(R.id.listView)
    ListView listView;
    ArrayAdapter<Contributor> adapter;


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

        String owner = "square";
        String repo = "retrofit";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/" + owner + "/" + repo + "/contributors")
                .build();

        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override public void onFailure(okhttp3.Call call, IOException e) {
                showToast(e.getMessage());
            }

            @Override public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                try {
                    String body = response.body().string();
                    Gson gson = new Gson();

                    final List<Contributor> contributorList = gson.fromJson(body, new TypeToken<List<Contributor>>() {
                    }.getType());

                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            adapter.addAll(contributorList);
                            adapter.notifyDataSetChanged();
                        }
                    });


                } catch (IOException e) {
                    showToast(e.getMessage());
                }
            }
        });

    }

    private void showToast(final String msg){
        runOnUiThread(new Runnable() {
            @Override public void run() {
                Toast.makeText(OkHttpActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
