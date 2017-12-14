package com.example.anushamodwal.myapplication.util;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadImageURL {
    public static String getProperty(String key,Context context) throws IOException {
        Properties properties = new Properties();;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(Constants.IMAGE_FILE_NAME);
        properties.load(inputStream);
        return properties.getProperty(key);

    }
}
