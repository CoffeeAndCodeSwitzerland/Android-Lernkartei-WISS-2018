package com.lernkarteia.bizf725004.android_lernkartei_wiss_2018;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Singleplayer extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return  inflater.inflate(R.layout.singleplayer, container, false);
    }
}