package com.lernkarteia.bizf725004.android_lernkartei_wiss_2018;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SingleplayerFragement extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
           return  inflater.inflate(R.layout.singleplayer, container, false);
    }
}