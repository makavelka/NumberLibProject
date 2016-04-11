package com.example.numberlib.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.numberlib.App;
import com.example.numberlib.model.IModel;
import com.example.numberlib.view.IView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

public class PresenterImpl implements Presenter {

    private static final String BUNDLE_KEY = "BUNDLE_KEY";
    private ArrayList<String> currentList;
    private IView view;

    @Inject
    IModel model;

    @Override
    public ArrayList<String> getConvertedData(InputStream stream) throws IOException {
        return model.getConvertedData(stream);
    }

    @Override
    public ArrayList<String> getRawData(InputStream stream) throws IOException {
        return model.getRawData(stream);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, IView view) {
        App.getAppComponent().inject(this);
        this.view = view;
        if (savedInstanceState != null) {
            currentList = (ArrayList<String>) savedInstanceState.getSerializable(BUNDLE_KEY);
            if (!isListEmpty()) {
                view.showData();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!isListEmpty()) {
            outState.putSerializable(BUNDLE_KEY, new ArrayList<>(currentList));
        }

    }

    @Inject
    @Override
    public InputStream loadStreamFromAssets(Context context){
        try {
            return context.getAssets().open("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isListEmpty() {
        return currentList == null || currentList.isEmpty();
    }
}
