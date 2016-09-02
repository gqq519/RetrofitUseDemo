package com.feicuiedu.demoretrofit.demoETV;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.feicuiedu.demoretrofit.R;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvInfoActivity extends AppCompatActivity {

    ListFragment categoryFragment;
    ListFragment channelFragment;
    ListFragment programFragment;

    ArrayAdapter<TvCategory> categoryAdapter;
    ArrayAdapter<TvChannel> channelAdapter;
    ArrayAdapter<TvProgram> programAdapter;

    TvApi tvApi;

    Call<TvRspBody<TvChannel>> channelCall;
    Call<TvRspBody<TvProgram>> programCall;

    Callback<TvRspBody<TvCategory>> categoryCallback = new Callback<TvRspBody<TvCategory>>() {
        @Override
        public void onResponse(Call<TvRspBody<TvCategory>> call, Response<TvRspBody<TvCategory>> response) {
            categoryAdapter.addAll(response.body().result);
            categoryAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<TvRspBody<TvCategory>> call, Throwable t) {
            showToast(t.getMessage());
        }
    };

    Callback<TvRspBody<TvChannel>> channelCallback = new Callback<TvRspBody<TvChannel>>() {
        @Override
        public void onResponse(Call<TvRspBody<TvChannel>> call, Response<TvRspBody<TvChannel>> response) {
            channelFragment.setListShown(true);
            channelAdapter.clear();
            channelAdapter.addAll(response.body().result);
            channelAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<TvRspBody<TvChannel>> call, Throwable t) {
            showToast(t.getMessage());
        }
    };

    Callback<TvRspBody<TvProgram>> programCallback = new Callback<TvRspBody<TvProgram>>() {
        @Override
        public void onResponse(Call<TvRspBody<TvProgram>> call, Response<TvRspBody<TvProgram>> response) {
            programFragment.setListShown(true);
            programAdapter.clear();
            if (response.body().result != null)
            programAdapter.addAll(response.body().result);
            programAdapter.notifyDataSetChanged();

        }

        @Override
        public void onFailure(Call<TvRspBody<TvProgram>> call, Throwable t) {
            showToast(t.getMessage());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_info);
        ButterKnife.bind(this);

        FragmentManager manager = getSupportFragmentManager();
        categoryFragment = (ListFragment) manager.findFragmentById(R.id.category);
        channelFragment = (ListFragment) manager.findFragmentById(R.id.channel);
        programFragment = (ListFragment) manager.findFragmentById(R.id.program);

        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        channelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        programAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        categoryFragment.setListAdapter(categoryAdapter);
        channelFragment.setListAdapter(channelAdapter);
        programFragment.setListAdapter(programAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TvApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tvApi = retrofit.create(TvApi.class);

        categoryFragment.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                channelFragment.setListShown(false);
                if (channelCall != null) channelCall.cancel();
                TvCategory tvCategory = categoryAdapter.getItem(position);
                channelCall = tvApi.getChannel(TvApi.KEY, tvCategory.id);
                channelCall.enqueue(channelCallback);

                programAdapter.clear();
                programAdapter.notifyDataSetChanged();
            }
        });

        channelFragment.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                programFragment.setListShown(false);
                if (programCall != null) programCall.cancel();
                TvChannel tvChannel = channelAdapter.getItem(position);
                programCall = tvApi.getProgram(TvApi.KEY, tvChannel.rel, "2016-05-13");
                programCall.enqueue(programCallback);
            }
        });

        programFragment.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("播出时间：" + programAdapter.getItem(position).time);
            }
        });


        Call<TvRspBody<TvCategory>> call = tvApi.getCategory(TvApi.KEY);
        call.enqueue(categoryCallback);

    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
