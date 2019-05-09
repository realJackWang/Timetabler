package com.botton.timetabler.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.botton.timetabler.R;
import com.botton.timetabler.activity.AddCourseActivity;
import com.botton.timetabler.activity.BottomBarActivity;
import com.botton.timetabler.activity.CourseHomeworkActivity;
import com.botton.timetabler.activity.LoginActivity;
import com.botton.timetabler.activity.TimeGraphActivity;
import com.botton.timetabler.activity.TodoListActivity;

/**
 * Created by Jill on 2018/7/21.
 */

public class DiscoverFragment extends Fragment {
    ImageView course_hw;
    ImageView time_graph;
    ImageView todo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        initview(view);
        return view;


    }
    public static DiscoverFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initview(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Button todo = view.findViewById(R.id.todo);
        Button course_hw = view.findViewById(R.id.course_hw);
        Button focus = view.findViewById(R.id.focus);
        Button time_graph = view.findViewById(R.id.time_graph);
        Button ring = view.findViewById(R.id.ring);


        course_hw.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CourseHomeworkActivity.class);
            getActivity().startActivityForResult(intent, 0);
        });
        time_graph.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), TimeGraphActivity.class);
            this.startActivityForResult(intent, 0);
        });
        todo.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), TimeGraphActivity.class);
            this.startActivityForResult(intent, 0);
        });

//        todo.setOnClickListener(v -> {
//            Intent intent = new Intent(getContext(), TodoListActivity.class);
//            this.startActivityForResult(intent, 0);
//        });
    }
}
