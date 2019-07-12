package com.example.techtreeandroid.di;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class FireauthModule {


    @Provides
    static FirebaseAuth providefirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Nullable
    static FirebaseUser providefirebaseUser(FirebaseAuth firebaseAuth) {
        return firebaseAuth.getCurrentUser();
    }
}
