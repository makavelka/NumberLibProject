package com.example.numberlib.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.numberlib.App;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getApplication()).getAppComponent().inject(this);
    }
}
