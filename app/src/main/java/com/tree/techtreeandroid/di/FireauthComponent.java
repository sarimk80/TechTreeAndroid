package com.tree.techtreeandroid.di;

import com.tree.techtreeandroid.fragments.User;
import com.tree.techtreeandroid.mainActivity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FireauthModule.class})
public interface FireauthComponent {

    void inject(MainActivity mainActivity);

    void injectFragment(User user);
}
