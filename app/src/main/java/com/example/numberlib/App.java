package com.example.numberlib;

import android.app.Application;
import android.content.Context;

import com.example.numberlib.di.component.DaggerNumbersComponent_NumbersAppComponent;
import com.example.numberlib.di.component.NumbersComponent;
import com.example.numberlib.di.modules.AppModule;

public class App extends Application {

    private static NumbersComponent.NumbersAppComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
    }

    public static NumbersComponent.NumbersAppComponent getAppComponent() {
        return appComponent;
    }

    public void buildComponent() {
        appComponent = DaggerNumbersComponent_NumbersAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
