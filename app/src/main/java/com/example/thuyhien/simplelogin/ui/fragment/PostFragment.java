package com.example.thuyhien.simplelogin.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;

import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PostFragment extends Fragment {

    public static PostFragment newInstance() {
        PostFragment postFragment = new PostFragment();
        Bundle bundle = new Bundle();
        postFragment.setArguments(bundle);
        return postFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViews();
    }

    private void initViews() {

    }
}
