package com.example.numberlib.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.numberlib.App;
import com.example.numberlib.R;
import com.example.numberlib.presenter.PresenterImpl;
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

    @Inject PresenterImpl presenter;
    private boolean isNums = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App)getApplication()).getAppComponent().inject(this);
        presenter.onCreate(savedInstanceState);
        initToolbar();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        try {
//            AssetManager manager = getAssets();
//            stringNums = c.convertNumsFromFile(manager.open("input.txt"));
//            nums = c.getNumsFromFile(manager.open("input.txt"));
//            switchList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @OnClick(R.id.switch_button_toolbar)
    public void switchList() throws IOException {
        InputStream stream = getAssets().open("input.txt");
        isNums = isNums ? false : true;
        if (isNums) {
            presenter.getRawData(stream);
        } else {
            presenter.getConvertedData(stream);
        }
    }

    @Override
    public void showData(ArrayList<String> list) {
        mNoData.setVisibility(android.view.View.GONE);
        mRecyclerView.setAdapter(new SimpleAdapter(list));
    }

    @Override
    public void showEmptyList() {
        mNoData.setVisibility(android.view.View.VISIBLE);
    }
}
