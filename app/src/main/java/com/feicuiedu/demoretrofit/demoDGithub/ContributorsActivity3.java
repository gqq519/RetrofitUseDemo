package com.feicuiedu.demoretrofit.demoDGithub;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicuiedu.demoretrofit.R;
import com.feicuiedu.demoretrofit.demoAOkHttp.Contributor;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContributorsActivity3 extends MvpLceActivity<SwipeRefreshLayout, List<Contributor>, ContributorsView, ContributorsPresenter>
        implements SwipeRefreshLayout.OnRefreshListener, ContributorsView{


    @Bind(R.id.listView)
    ListView listView;

    private ArrayAdapter<Contributor> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries_list);
        ButterKnife.bind(this);

        contentView.setOnRefreshListener(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        loadData(false);
    }

    @Override protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (pullToRefresh) {
            return "Error while loading countries";
        } else {
            return "Error while loading countries. Click here to retry";
        }
    }

    @NonNull @Override public ContributorsPresenter createPresenter() {
        return new ContributorsPresenter();
    }

    @Override public void setData(List<Contributor> data) {
        adapter.clear();
        Collections.shuffle(data);
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override public void loadData(boolean pullToRefresh) {
        presenter.loadContributors(pullToRefresh);
    }

    @Override public void onRefresh() {
        loadData(true);
    }
}
