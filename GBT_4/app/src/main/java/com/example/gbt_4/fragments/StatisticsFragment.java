package com.example.gbt_4.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gbt_4.R;
import com.example.gbt_4.SmokingGraph;

public class StatisticsFragment extends Fragment {

    Button btn_statistics_show_graph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_statistics, container, false);

        btn_statistics_show_graph = (Button) v.findViewById(R.id.btn_statistics_show_graph);
        btn_statistics_show_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SmokingGraph.class);
                startActivity(intent);
            }
        });




        return  v;
    }
}