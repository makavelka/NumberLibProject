package com.example.numberlib.model;

import com.example.numberlib.App;
import com.example.numberlib.NumberLib;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

public class ModelImpl implements IModel {

    @Inject NumberLib numberLib;

    public ModelImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public ArrayList<String> getConvertedData(InputStream stream) throws IOException {
        return numberLib.convertNumsFromFile(stream);
    }

    @Override
    public ArrayList<String> getRawData(InputStream stream) throws IOException {
        return numberLib.getNumsFromFile(stream);
    }
}
