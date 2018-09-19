package com.project.android.democrudrealm.module.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.project.android.democrudrealm.R;
import com.project.android.democrudrealm.module.adapter.BookAdapter;
import com.project.android.democrudrealm.module.helper.RealmHelper;
import com.project.android.democrudrealm.module.model.DataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    private RealmHelper realmHelper;
    private ArrayList<DataModel> dataModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dataModels = new  ArrayList<DataModel>();
        realmHelper = new RealmHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
        setRecyclerView();
    }

    private void setRecyclerView() {
        try{
            dataModels = realmHelper.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        BookAdapter bookAdapter = new BookAdapter(dataModels, new BookAdapter.OnItemClickListener() {
            @Override
            public void onClick(DataModel item) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("title", item.getTitle());
                intent.putExtra("description", item.getDescription());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(bookAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            dataModels = realmHelper.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        setRecyclerView();
    }
}
