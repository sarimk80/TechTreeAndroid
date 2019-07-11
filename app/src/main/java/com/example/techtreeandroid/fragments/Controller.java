package com.example.techtreeandroid.fragments;


import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.interfaces.BluetoothCallback;
import me.aflak.bluetooth.interfaces.DeviceCallback;
import me.aflak.bluetooth.interfaces.DiscoveryCallback;


public class Controller extends Fragment {


    private Bluetooth bluetooth;

    private ListView listView;
    private CustomAdapter adapter;
    private static final String TAG = "Controller";
    private AlertDialog.Builder alert;
    private AlertDialog alertDialog;

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

        alert = new AlertDialog.Builder(getContext());
        alert.setMessage("Connecting.....");
        alert.setIcon(getResources().getDrawable(R.drawable.ic_bluetooth));


        bluetooth = new Bluetooth(getContext());
        bluetooth.setBluetoothCallback(bluetoothCallback);
        bluetooth.setDeviceCallback(bluetoothDeviceCallback);
        bluetooth.setDiscoveryCallback(bluetoothDiscoveryCallback);


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
                alertDialog = alert.create();
                alertDialog.show();

                try {
                    bluetooth.connectToAddress(bluetooth.getPairedDevices().get(position).getAddress());
                    // alertDialog.dismiss();

                } catch (Exception e) {
                    Toasty.error(getContext(), "Error " + e, Toast.LENGTH_LONG).show();
                }


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
            alertDialog.dismiss();
        }

        @Override
        public void onDeviceDisconnected(BluetoothDevice device, String message) {

        }

        @Override
        public void onMessage(String message) {

        }

        @Override
        public void onError(int errorCode) {
            Toasty.error(Objects.requireNonNull(getContext()), "Cannot connect Device ", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onConnectError(BluetoothDevice device, String message) {

            new Handler(Looper.getMainLooper())
                    .post(() ->

                    {
                        Toasty.error(Objects.requireNonNull(getContext()),
                                "Cannot Connect to " + device, Toast.LENGTH_LONG)
                                .show();
                        alertDialog.dismiss();
                    });


        }

    };


    private DiscoveryCallback bluetoothDiscoveryCallback = new DiscoveryCallback() {
        @Override
        public void onDiscoveryStarted() {

        }

        @Override
        public void onDiscoveryFinished() {

        }

        @Override
        public void onDeviceFound(BluetoothDevice device) {

        }

        @Override
        public void onDevicePaired(BluetoothDevice device) {

        }

        @Override
        public void onDeviceUnpaired(BluetoothDevice device) {

        }

        @Override
        public void onError(int errorCode) {
            Toasty.error(Objects.requireNonNull(getContext()), "Cannot connect to Device", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        bluetooth.onStop();
        bluetooth.disable();
    }


}
