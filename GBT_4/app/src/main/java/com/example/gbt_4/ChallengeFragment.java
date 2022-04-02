package com.example.gbt_4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbt_4.info.PracticeDto;

public class ChallengeFragment extends Fragment {

    TextView tv_name, tv_age;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_challenge, container, false);


        Bundle bundle = getArguments();
        PracticeDto practiceDto = (PracticeDto) bundle.getSerializable("user");

        tv_name = (TextView)v.findViewById(R.id.tv_name);
        tv_age = (TextView)v.findViewById(R.id.tv_age);

        tv_name.setText(practiceDto.getName());
        tv_age.setText(""+practiceDto.getAge());
                return v;
    }
}