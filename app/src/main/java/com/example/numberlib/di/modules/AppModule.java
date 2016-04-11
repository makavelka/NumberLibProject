package com.example.numberlib.di.modules;

import android.app.Application;
import android.content.Context;

import com.example.numberlib.App;
import com.example.numberlib.NumberLib;
import com.example.numberlib.model.IModel;
import com.example.numberlib.model.ModelImpl;
import com.example.numberlib.presenter.Presenter;
import com.example.numberlib.presenter.PresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public NumberLib provideNumberLibClass() {
        NumberLib numberLib = new NumberLib();
        return numberLib;
    }

    @Provides
    @Singleton
    IModel provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    @Singleton
    Presenter providePresenter() {
        return new PresenterImpl();
    }
}
