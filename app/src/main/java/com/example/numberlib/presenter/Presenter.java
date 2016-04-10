package com.example.numberlib.presenter;

import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface Presenter {
    ArrayList<String> getConvertedData(InputStream stream) throws IOException;
    ArrayList<String> getRawData(InputStream stream) throws IOException;
    void onCreate(Bundle savedInstanceState);
    void onSaveInstanceState(Bundle outState);
}
