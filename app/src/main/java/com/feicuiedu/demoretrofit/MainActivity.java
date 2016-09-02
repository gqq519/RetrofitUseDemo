package com.feicuiedu.demoretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicuiedu.demoretrofit.demoAOkHttp.OkHttpActivity;
import com.feicuiedu.demoretrofit.demoBGithub.ContributorsActivity1;
import com.feicuiedu.demoretrofit.demoCGithub.ContributorsActivity2;
import com.feicuiedu.demoretrofit.demoDGithub.ContributorsActivity3;
import com.feicuiedu.demoretrofit.demoETV.TvInfoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Bind(R.id.listView)
    ListView listView;

    private Demo[] demos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        demos = createDemos();
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demos));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(demos[position].intent);
    }

    private Demo[] createDemos(){
        return new Demo[]{
                new Demo("OkHttp Get Example", new Intent(this, OkHttpActivity.class)),
                new Demo("Retrofit Get Example", new Intent(this, ContributorsActivity1.class)),
                new Demo("Get Example with GsonConverterFactory", new Intent(this, ContributorsActivity2.class)),
                new Demo("Retrofit and Mvp", new Intent(this, ContributorsActivity3.class)),
                new Demo("Post Formed-encoded Data", new Intent(this, TvInfoActivity.class))
        };
    }

    static class Demo {
        String name;
        Intent intent;

        private Demo(String name, Intent intent) {
            this.name = name;
            this.intent = intent;
        }

        public String toString() {
            return name;
        }
    }
}
