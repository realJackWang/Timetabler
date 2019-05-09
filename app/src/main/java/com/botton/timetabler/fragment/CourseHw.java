package com.botton.timetabler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botton.timetabler.R;

/**
 * Created by bzdell on 2019/5/6.
 */

public class CourseHw extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_college, container, false);
        return view;
    }

    public static CollegeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        CollegeFragment fragment = new CollegeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
