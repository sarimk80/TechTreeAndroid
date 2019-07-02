package com.example.techtreeandroid.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.techtreeandroid.R;
import com.example.techtreeandroid.databinding.FragmentArduinoBinding;
import com.example.techtreeandroid.fragmentevent.ArduinoEvent;
import com.example.techtreeandroid.fragmentmodel.ArduinoModel;


public class Arduino extends Fragment {
    private FragmentArduinoBinding arduinoBinding;

    public Arduino() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        arduinoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_arduino, container, false);
        return arduinoBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arduinoBinding.setModel(new ArduinoModel("Arduino", "Product"));

        arduinoBinding.setEvent(new ArduinoEvent() {
            @Override
            public void ControllerEvent() {
                Navigation.findNavController(arduinoBinding.getRoot()).navigate(R.id.action_arduino_to_ard_Detail);
            }

            @Override
            public void ProductEvent() {
                Navigation.findNavController(arduinoBinding.getRoot()).navigate(R.id.action_arduino_to_prod_Deatil);
            }
        });
    }
}
