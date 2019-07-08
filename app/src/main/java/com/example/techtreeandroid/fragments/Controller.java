package com.example.techtreeandroid.fragments;


import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.techtreeandroid.R;
import com.example.techtreeandroid.fragmentadapter.CustomAdapter;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.interfaces.BluetoothCallback;
import me.aflak.bluetooth.interfaces.DeviceCallback;


public class Controller extends Fragment {


    private Bluetooth bluetooth;

    private ListView listView;
    private CustomAdapter adapter;

    public Controller() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_bluetooth, container, false);


        listView = view.findViewById(R.id.list_item);
        listView.setDividerHeight(2);


        bluetooth = new Bluetooth(getContext());
        bluetooth.setBluetoothCallback(bluetoothCallback);
        bluetooth.setDeviceCallback(bluetoothDeviceCallback);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //RequestPermission();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bluetooth.onStart();

        if (!bluetooth.isEnabled()) {
            bluetooth.enable();
        }

    }


    private BluetoothCallback bluetoothCallback = new BluetoothCallback() {
        @Override
        public void onBluetoothTurningOn() {

        }

        @Override
        public void onBluetoothOn() {

            adapter = new CustomAdapter(getContext(), bluetooth.getPairedDevices());
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setOnItemClickListener((adapterView, view, position, l) -> {
                //bluetooth.connectToAddress();

            });


        }

        @Override
        public void onBluetoothTurningOff() {

        }

        @Override
        public void onBluetoothOff() {

        }

        @Override
        public void onUserDeniedActivation() {

        }
    };


    private DeviceCallback bluetoothDeviceCallback = new DeviceCallback() {
        @Override
        public void onDeviceConnected(BluetoothDevice device) {

        }

        @Override
        public void onDeviceDisconnected(BluetoothDevice device, String message) {

        }

        @Override
        public void onMessage(String message) {

        }

        @Override
        public void onError(int errorCode) {

        }

        @Override
        public void onConnectError(BluetoothDevice device, String message) {
            Toasty.error(getContext(), "Cannot connect to " + device.getName(), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bluetooth.isEnabled() || bluetooth.isConnected()) {
            bluetooth.onStop();
            bluetooth.disable();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        bluetooth.onStop();
        bluetooth.disable();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (bluetooth.isEnabled() || bluetooth.isConnected()) {
            bluetooth.onStop();
            bluetooth.disable();
        }

    }
}
