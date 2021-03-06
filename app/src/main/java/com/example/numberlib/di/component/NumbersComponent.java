package com.example.numberlib.di.component;

import com.example.numberlib.di.modules.AppModule;
import com.example.numberlib.model.ModelImpl;
import com.example.numberlib.presenter.PresenterImpl;
import com.example.numberlib.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

public class NumbersComponent {

    @Singleton
    @Component(modules = AppModule.class)

    public interface NumbersAppComponent {
        void inject(MainActivity activity);
        void inject(ModelImpl model);
        void inject(PresenterImpl presenter);
    }
}
