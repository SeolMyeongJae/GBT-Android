package com.example.gbt_4.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gbt_4.CreateCustomChallenge;
import com.example.gbt_4.R;

public class CustomChallengeFragment extends Fragment {

    Button btn_create_custom_challenge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_custom_challenge, container, false);

        btn_create_custom_challenge = (Button) v.findViewById(R.id.btn_create_custom_challenge);
        btn_create_custom_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateCustomChallenge.class);
                startActivity(intent);
            }
        });

        return v;
    }
}