package com.example.techtreeandroid.fragments;


import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;


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
import com.example.techtreeandroid.databinding.FragmentBluetoothBinding;
import com.example.techtreeandroid.fragmentadapter.CustomAdapter;
import com.example.techtreeandroid.fragmentmodel.ConstrollerModel;

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

    //private ListView listView;
    private CustomAdapter adapter;
    private static final String TAG = "Controller";
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
            bluetoothBinding.listItem.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            bluetoothBinding.listItem.setOnItemClickListener((adapterView, view, position, l) -> {
                bluetoothBinding.loading.setVisibility(View.VISIBLE);

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
            Log.d(TAG, "onDeviceConnected: " + device.getName());
            new Handler(Looper.getMainLooper()).post(() -> {
                bluetoothBinding.listItem.setVisibility(View.GONE);
                bluetoothBinding.loading.setVisibility(View.GONE);
                bluetoothBinding.setModel(new ConstrollerModel(device.getAddress(),
                        device.getName(), String.valueOf(device.getBondState())));
            });

            // bluetooth.send("s");
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
