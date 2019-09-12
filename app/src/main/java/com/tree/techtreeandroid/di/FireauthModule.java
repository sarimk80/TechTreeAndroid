package com.tree.techtreeandroid.di;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class FireauthModule {


    @Provides
    @Singleton
    static FirebaseAuth providefirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    @Nullable
    static FirebaseUser providefirebaseUser(FirebaseAuth firebaseAuth) {
        return firebaseAuth.getCurrentUser();
    }
}
