package com.example.techtreeandroid.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.techtreeandroid.R;
import com.example.techtreeandroid.databinding.FragmentUserBinding;

//import com.example.techtreeandroid.di.DaggerGoogleSignComponent;
import com.example.techtreeandroid.di.FireauthComponent;

import com.example.techtreeandroid.di.baseapplication.BaseApplication;
import com.example.techtreeandroid.fragmentmodel.UserModel;
import com.example.techtreeandroid.mainActivity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class User extends Fragment {

    @Inject
    public FirebaseAuth auth;

    @Nullable
    @Inject
    public FirebaseUser user;

    FragmentUserBinding userBinding;

    public User() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FireauthComponent fireauthComponent = ((BaseApplication) getActivity().getApplication()).getFireauthComponent();
        fireauthComponent.injectFragment(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);


        // auth = FirebaseAuth.getInstance();
        //user = auth.getCurrentUser();
        userBinding.setUser(new UserModel(user.getDisplayName(), user.getEmail(), user.getPhoneNumber()));
        Glide.with(userBinding.getRoot()).load(user.getPhotoUrl()).into(userBinding.logo);

        userBinding.setEvent(() -> {
            auth.signOut();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);

        });
        return userBinding.getRoot();
    }

}
