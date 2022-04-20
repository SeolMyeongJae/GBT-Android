package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.utils.FSize;

import java.util.ArrayList;

public class SmokingGraph extends Activity {

    Button btn_graph_back;

    ArrayList<Integer> jsonList = new ArrayList<>(); // ArrayList 선언
    ArrayList<String> labelList = new ArrayList<>(); // ArrayList 선언
    BarChart barChart;
    TextView minuteTextView;

    ArrayList<Entry> entryArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoking_graph);
        setTitle("");

        barChart = (BarChart) findViewById(R.id.bc_smoking);
        //그래프 기본 셋팅
        graphInitSetting();

        BarChartGraph(labelList,jsonList);
        barChart.setTouchEnabled(false); //확대 방지
        //최댓값 설정 안하면 자동적으로 막대크기 변경됨
//        barChart.getAxisRight().setAxisMaxValue(30);
//        barChart.getAxisLeft().setAxisMaxValue(30);


    LineData smokingData = new LineData();

    LineDataSet lineDataSet = new LineDataSet(entryArrayList, "흡연 갯수");
    smokingData.addDataSet(lineDataSet);

        btn_graph_back = (Button) findViewById(R.id.btn_graph_back);
        btn_graph_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //그래프 더미 데이터
    private void graphInitSetting() {
        labelList.add("일");
        labelList.add("월");
        labelList.add("화");
        labelList.add("수");
        labelList.add("목");
        labelList.add("금");
        labelList.add("토");

        jsonList.add(10);
        jsonList.add(8);
        jsonList.add(7);
        jsonList.add(8);
        jsonList.add(3);
        jsonList.add(5);
        jsonList.add(2);

        //x축 스타일
        XAxis xAxis;
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(16);

    }

    //그래프 함수
    private void BarChartGraph(ArrayList<String> labelList, ArrayList<Integer> valList) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0;i<valList.size();i++){
            entries.add(new BarEntry((Integer) valList.get(i), i));
        }

        BarDataSet depenses = new BarDataSet(entries,"흡연 횟수");
        depenses.setAxisDependency(YAxis.AxisDependency.LEFT);
        barChart.setDescription("");
        depenses.setValueTextColor(Color.WHITE);

        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < labelList.size();i++){
           labels.add((String) labelList.get(i));
        }

        BarData data = new BarData(labels, depenses);
        depenses.setColors(ColorTemplate.JOYFUL_COLORS);

        barChart.setData(data);
        barChart.animateXY(1000,1000);
        barChart.invalidate();
    }
}