package com.example.numberlib.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface IModel {
    ArrayList<String> getConvertedData(InputStream stream) throws IOException;
    ArrayList<String> getRawData(InputStream stream) throws IOException;
}
