package com.botton.timetabler.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.botton.timetabler.R;
import com.botton.timetabler.activity.MapActivity;

/**
 * Created by bzdell on 2018/7/21.
 */

public class CollegeFragment extends Fragment {
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_college, container, false);
        imageView = view.findViewById(R.id.imageView);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),MapActivity.class);
            this.startActivity(intent);
        });
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
