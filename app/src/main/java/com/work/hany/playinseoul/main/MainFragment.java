package com.work.hany.playinseoul.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.hany.playinseoul.R;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    @Inject
    public MainFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,null,false);
        return rootView;
    }
}
