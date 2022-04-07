package com.example.gbt_4;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OfficialChallengeAdapter extends BaseAdapter {

    //날짜 계산
    String today, startDate, endDate;
    Date format1 = new SimpleDateFormat("yyyy/MM/dd").parse(today);
    Date format2 = new SimpleDateFormat("yyyy/MM/dd").parse(startDate);
    Date format3 = new SimpleDateFormat("yyyy/MM/dd").parse(endDate);

    Long piriodSec = (format3.getTime() - format2.getTime()) / 1000;
    Long piriodDays= piriodSec / (24*60*60);

    Long dSec = (format2.getTime() - format1.getTime()) / 1000;
    Long dDay = dSec / (24*60*60);

    //     어댑터를 쓸때 필요한 부분들을 가져오는 생성자를 만든다.
//     context =리스트를 보여주는 액티비티의 Contenxt : itemlayout을 inflate를 할때 필요하다.
//     layout  =데이터들을 담아둘 itemlayout Address : R.layout.sample_item 이들어오는 곳이다. 리소스 디렉토리내의 주소를 가져오므로 int로 저장된다.
//     items  =데이터 꾸러미를 가져온다.
    Context context;
    int layout;
    ArrayList<ChallengeItem> items;

    public OfficialChallengeAdapter(Context context, int layout, ArrayList<ChallengeItem> items) throws java.text.ParseException {
        this.context = context;
        this.layout = layout;
        this.items = items;
    }

    //어댐터를 사용하는 중간에 데이터가 바뀌었을때
    //@param =items
    public void setItems(ArrayList<ChallengeItem> items) {
        this.items = items;
        notifyDataSetChanged(); //어댑터의 정보가 바뀌면 리스트뷰가 리프레시 하는 메소드
    }

    //데이터의 숫자를 리턴 해줄 때
    //@return
    @Override
    public int getCount() {
        if(items == null) return 0;
        else return items.size();
    }

    //선택된 데이터를 리턴 할 때
    //position = 위치에 따른 데이터
    //@return = position에 해당되는 data를 리턴
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    //식별 가능한 수치를 리턴
    //@param position 위치
    //@return 위치를 식별자로 사용한다.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //보여줄 화면을 만드는 메서드
    //position =식별자를 리턴
    //view =보여줄 화면(초기엔 null값이며, 'BaseAdapter'를 그냥 쓸 경우엔 텍스트 형태로 리턴할 수 있다
    //@return = layout에 맞춰서 데이터를 넣은 형태를 리턴
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) throws ParseException {
        //view가 null인지 판단
        if(view == null){
            //view에 내가 원하는 layout 화면을 넣어준다
            view = view.inflate(context, layout, null);
        }
        //position에 따라 다른 정보를 보여주므로 데이터 꾸러미에서 position에 따른 데이터를 가져온다.
        ChallengeItem challengeItem = items.get(position);

        //layout에 들어가있는 객체들을 연걸
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_endDate = (TextView) view.findViewById(R.id.tv_endDate);
        TextView tv_summary = (TextView) view.findViewById(R.id.tv_goal);
        TextView tv_memberCount = (TextView) view.findViewById(R.id.tv_memberCount);
        TextView tv_period = (TextView) view.findViewById(R.id.tv_period);
        ImageView iv_profilePhoto = (ImageView) view.findViewById(R.id.iv_profilePhoto);

        tv_title.setText(challengeItem.getTitle());
        tv_endDate.setText(challengeItem.getEndDate().toString());
        tv_summary.setText(challengeItem.getSummary());
//        tv_memberCount.setText(challengeItem.getmember);
        tv_period.setText(piriodDays.toString());
        Uri uri = Uri.parse("https://gbt-s3.s3.us-west-1.amazonaws.com/static/%EC%9A%B4%EC%A0%84%EB%A9%B4%ED%97%88%EC%A6%9D.jpg");
        iv_profilePhoto.setImageURI(uri);
        return view;
    }



}
