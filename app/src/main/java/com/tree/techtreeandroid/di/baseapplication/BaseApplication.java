package com.tree.techtreeandroid.di.baseapplication;

import android.app.Application;

import com.tree.techtreeandroid.di.DaggerFireauthComponent;
import com.tree.techtreeandroid.di.FireauthComponent;

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
