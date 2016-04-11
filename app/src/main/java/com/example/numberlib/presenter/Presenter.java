package com.example.numberlib.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.numberlib.view.IView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface Presenter {
    ArrayList<String> getConvertedData(InputStream stream) throws IOException;
    ArrayList<String> getRawData(InputStream stream) throws IOException;
    void onCreate(Bundle savedInstanceState, IView view);
    void onSaveInstanceState(Bundle outState);
    InputStream loadStreamFromAssets(Context context) throws IOException;
}
