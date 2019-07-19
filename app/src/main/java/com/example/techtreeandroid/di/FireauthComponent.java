package com.example.techtreeandroid.di;

import com.example.techtreeandroid.fragments.User;
import com.example.techtreeandroid.mainActivity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FireauthModule.class})
public interface FireauthComponent {

    void inject(MainActivity mainActivity);

    void injectFragment(User user);
}
