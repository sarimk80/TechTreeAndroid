package com.example.techtreeandroid.di.baseapplication;

import android.app.Application;

import com.example.techtreeandroid.di.DaggerFireauthComponent;
import com.example.techtreeandroid.di.FireauthComponent;

public class BaseApplication extends Application {

    private FireauthComponent fireauthComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        fireauthComponent = DaggerFireauthComponent.create();
    }

    public FireauthComponent getFireauthComponent() {
        return fireauthComponent;
    }
}
