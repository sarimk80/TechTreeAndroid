package com.tree.techtreeandroid.log_out;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.tree.techtreeandroid.R;
import com.tree.techtreeandroid.databinding.ActivityLogOutBinding;
import com.tree.techtreeandroid.log_out.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Log_out extends AppCompatActivity {

    private FirebaseUser user;
    private ActivityLogOutBinding logOutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_log_out);
        logOutBinding = DataBindingUtil.setContentView(this, R.layout.activity_log_out);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            logOutBinding.setUser(new User(user.getDisplayName(), user.getEmail(), user.getPhoneNumber()));
        } else {
            logOutBinding.setUser(new User("TechTree", "TechTree", "000"));
        }
    }
}
