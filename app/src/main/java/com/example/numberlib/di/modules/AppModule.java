package com.example.numberlib.di.modules;

import android.app.Application;

import com.example.numberlib.App;
import com.example.numberlib.NumberLib;
import com.example.numberlib.model.IModel;
import com.example.numberlib.model.ModelImpl;
import com.example.numberlib.presenter.Presenter;
import com.example.numberlib.presenter.PresenterImpl;
import com.example.numberlib.view.IView;

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
    Presenter providePresenter(IView view) {
        return new PresenterImpl(view);
    }
}
