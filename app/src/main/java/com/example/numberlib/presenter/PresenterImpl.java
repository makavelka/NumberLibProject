package com.example.numberlib.presenter;

import android.os.Bundle;

import com.example.numberlib.App;
import com.example.numberlib.model.ModelImpl;
import com.example.numberlib.view.IView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

public class PresenterImpl implements Presenter {

    private static final String BUNDLE_KEY = "BUNDLE_KEY";
    private ArrayList<String> currentList;
    private IView view;

    @Inject ModelImpl model;

    @Inject
    public PresenterImpl(IView view) {
        this.view = view;
    }

    @Override
    public ArrayList<String> getConvertedData(InputStream stream) throws IOException {
        return model.getConvertedData(stream);
    }

    @Override
    public ArrayList<String> getRawData(InputStream stream) throws IOException {
        return model.getRawData(stream);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        if (savedInstanceState != null) {
            currentList = (ArrayList<String>) savedInstanceState.getSerializable(BUNDLE_KEY);
            if (!isListEmpty()) {
                view.showData(currentList);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!isListEmpty()) {
            outState.putSerializable(BUNDLE_KEY, new ArrayList<>(currentList));
        }

    }

    private boolean isListEmpty() {
        return currentList == null || currentList.isEmpty();
    }
}
