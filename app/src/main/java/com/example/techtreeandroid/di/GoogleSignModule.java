//package com.example.techtreeandroid.di;
//
//import android.content.Context;
//
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class GoogleSignModule {
//
//    private Context context;
//
//    public GoogleSignModule(Context context) {
//        this.context = context;
//    }
//
//
//    @Provides
//    GoogleSignInOptions provideGso() {
//        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("635330782929-o56ifum8ccae7ivvr1bk5k1cbumbtr3b.apps .googleusercontent.com")
//                .requestEmail()
//                .build();
//    }
//
//    @Provides
//    GoogleSignInClient provideGoogleSign(GoogleSignInOptions gso) {
//        return GoogleSignIn.getClient(context, gso);
//    }
//}
