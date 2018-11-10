package com.botton.timetabler.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botton.timetabler.R;

/**
 * Created by bzdell on 2018/7/21.
 */

public class TimeTableFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        return view;
    }
/*
    public static TimeTableFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        TimeTableFragment fragment = new TimeTableFragment();
        fragment.setArguments(args);
        return fragment;
    }
    */
}
