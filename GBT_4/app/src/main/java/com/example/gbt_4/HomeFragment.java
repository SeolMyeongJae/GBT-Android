package com.example.gbt_4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gbt_4.info.PracticeDto;

public class HomeFragment extends Fragment {

    TextView tv_userName, tv_comment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        tv_userName = (TextView)v.findViewById(R.id.tv_userName);
        tv_comment = (TextView)v.findViewById(R.id.tv_comment);

        Bundle bundle = getArguments();
        GetUserDto getUserDto = (GetUserDto) bundle.getSerializable("user");


        tv_userName = (TextView)v.findViewById(R.id.tv_userName);
        tv_comment = (TextView)v.findViewById(R.id.tv_comment);

//        System.out.println(getUserDto.toString());

        tv_userName.setText(getUserDto.getUserName());
        tv_comment.setText(getUserDto.getComment());
        return v;
    }
}