package com.tree.techtreeandroid.fragments;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Toast;


import com.tree.techtreeandroid.R;
import com.tree.techtreeandroid.databinding.FragmentBluetoothBinding;
import com.tree.techtreeandroid.fragmentadapter.CustomAdapter;
import com.tree.techtreeandroid.fragmentmodel.ConstrollerModel;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.interfaces.BluetoothCallback;
import me.aflak.bluetooth.interfaces.DeviceCallback;
import me.aflak.bluetooth.interfaces.DiscoveryCallback;


public class Controller extends Fragment {


    private Bluetooth bluetooth;

    //private static final String TAG = "Controller";
    private FragmentBluetoothBinding bluetoothBinding;

    public Controller() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bluetoothBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bluetooth, container, false);
        //View view = inflater.inflate(R.layout.fragment_bluetooth, container, false);


        //listView = view.findViewById(R.id.list_item);
        bluetoothBinding.listItem.setDividerHeight(2);
        //listView.setDividerHeight(2);


        bluetooth = new Bluetooth(getContext());

        bluetooth.setBluetoothCallback(bluetoothCallback);
        bluetooth.setDeviceCallback(bluetoothDeviceCallback);
        bluetooth.setDiscoveryCallback(bluetoothDiscoveryCallback);


        return bluetoothBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        bluetooth.onStart();

        if (!bluetooth.isEnabled()) {
            bluetooth.enable();
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bluetoothBinding.imgFor.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    bluetooth.send("F");
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    bluetooth.send("S");
                    break;
                }
            }

            return false;
        });

        bluetoothBinding.imgBack.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    bluetooth.send("B");
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    bluetooth.send("S");
                    break;
                }
            }
            return false;
        });

        bluetoothBinding.imgLeft.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    bluetooth.send("L");
                    break;
                case MotionEvent.ACTION_UP:
                    bluetooth.send("S");
                    break;
            }
            return false;
        });


        bluetoothBinding.imgRight.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    bluetooth.send("R");
                    break;
                case MotionEvent.ACTION_UP:
                    bluetooth.send("S");
                    break;
            }

            return false;
        });

    }


    private BluetoothCallback bluetoothCallback = new BluetoothCallback() {
        @Override
        public void onBluetoothTurningOn() {

        }

        @Override
        public void onBluetoothOn() {

            //private ListView listView;
            CustomAdapter adapter = new CustomAdapter(getContext(), bluetooth.getPairedDevices());
            bluetoothBinding.listItem.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            bluetoothBinding.listItem.setOnItemClickListener((adapterView, view, position, l) -> {
                bluetoothBinding.loading.setVisibility(View.VISIBLE);

                try {
                    bluetooth.connectToAddress(bluetooth.getPairedDevices().get(position).getAddress());
                    // alertDialog.dismiss();

                } catch (Exception e) {
                    Toasty.error(Objects.requireNonNull(getContext()), "Error " + e, Toast.LENGTH_LONG).show();
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

            new Handler(Looper.getMainLooper()).post(() -> {
                bluetoothBinding.listItem.setVisibility(View.GONE);
                bluetoothBinding.loading.setVisibility(View.GONE);
                bluetoothBinding.setModel(new ConstrollerModel(device.getAddress(),
                        device.getName(), String.valueOf(device.getBondState())));


            });


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
                                "Cannot Connect to " + device.getName(), Toast.LENGTH_LONG)
                                .show();
                        bluetoothBinding.loading.setVisibility(View.GONE);

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
