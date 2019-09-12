package com.tree.techtreeandroid.arduinodetails;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tree.techtreeandroid.R;
import com.tree.techtreeandroid.databinding.FragmentArdDetailBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ard_Detail extends Fragment {

    FragmentArdDetailBinding fragmentArdDetailBinding;

    public Ard_Detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentArdDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ard__detail, container, false);
        fragmentArdDetailBinding.setModel(new ArduinoModel("Arduino", getString(R.string.Arduino)));
        return fragmentArdDetailBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentArdDetailBinding.setEvent(new Event() {
            @Override
            public void PowerJack() {
                fragmentArdDetailBinding.setModel(new ArduinoModel("Power Jack", getString(R.string.PowerJack)));
            }

            @Override
            public void DigitalIO() {
                fragmentArdDetailBinding.setModel(new ArduinoModel("Digital I/O", getString(R.string.DigitalIO)));
            }

            @Override
            public void MicroController() {
                fragmentArdDetailBinding.setModel(new ArduinoModel("MicroController", getString(R.string.MicroController)));
            }

            @Override
            public void UsbTypeB() {
                fragmentArdDetailBinding.setModel(new ArduinoModel("USB Type B", getString(R.string.UsbTypeB)));
            }

            @Override
            public void AnalogIO() {
                fragmentArdDetailBinding.setModel(new ArduinoModel("Analog I/O", getString(R.string.AnalogIO)));
            }

            @Override
            public void Reset() {
                fragmentArdDetailBinding.setModel(new ArduinoModel("Reset", getString(R.string.Reset)));
            }

            @Override
            public void PowerPin() {
                fragmentArdDetailBinding.setModel(new ArduinoModel("Power Pin", getString(R.string.PowerPins)));
            }
        });
    }
}
