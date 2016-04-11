package com.example.numberlib.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.numberlib.App;
import com.example.numberlib.R;
import com.example.numberlib.presenter.Presenter;
import com.example.numberlib.view.adapter.SimpleAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {

    @Bind(R.id.nums_recyclerView_mainActivity)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.nodata_textView_mainActivity)
    TextView mNoData;

    @Inject
    Presenter presenter;
    private boolean isNums = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App)getApplication()).getAppComponent().inject(this);
        presenter.onCreate(savedInstanceState, this);
        initToolbar();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        showData();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @OnClick(R.id.switch_button_toolbar)
    public void switchList() {
        isNums = isNums ? false : true;
        showData();
    }

    @Override
    public void showData() {
        ArrayList<String> list = new ArrayList<>();
        try {
            InputStream stream = presenter.loadStreamFromAssets(this);
            if (isNums) {
                list = presenter.getRawData(stream);
            } else {
                list = presenter.getConvertedData(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isListEmpty(list)) {
            mNoData.setVisibility(android.view.View.GONE);
            mRecyclerView.setAdapter(new SimpleAdapter(list));
        } else {
            showEmptyList();
        }
    }

    private boolean isListEmpty(ArrayList<String> list) {
        return list != null || list.size() > 0;
    }

    @Override
    public void showEmptyList() {
        mNoData.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
