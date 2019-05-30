package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class NoiDungTabFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.noidung_fragment, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());



        textView.setTextSize(Float.valueOf(sharedPreferences.getString("text_size", "10")));

        String text = getArguments().getString("data");
        textView.setText(text);
        return view;
    }
}



