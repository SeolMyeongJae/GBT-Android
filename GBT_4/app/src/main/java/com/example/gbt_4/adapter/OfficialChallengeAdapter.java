package com.example.gbt_4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gbt_4.R;
import com.example.gbt_4.dto.GetOfficialChallengeDto;
import com.example.gbt_4.fragments.OfficialChallengeFragment;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class OfficialChallengeAdapter extends BaseAdapter {
//
//    //날짜 계산
//    String today, startDate, endDate;
//    Date format1 = new SimpleDateFormat("yyyy/MM/dd").parse(today);
//    Date format2 = new SimpleDateFormat("yyyy/MM/dd").parse(startDate);
//    Date format3 = new SimpleDateFormat("yyyy/MM/dd").parse(endDate);
//
//    Long piriodSec = (format3.getTime() - format2.getTime()) / 1000;
//    Long piriodDays = piriodSec / (24 * 60 * 60);
//
//    Long dSec = (format2.getTime() - format1.getTime()) / 1000;
//    Long dDay = dSec / (24 * 60 * 60);

    //     어댑터를 쓸때 필요한 부분들을 가져오는 생성자를 만든다.
//     context =리스트를 보여주는 액티비티의 Contenxt : itemlayout을 inflate를 할때 필요하다.
//     layout  =데이터들을 담아둘 itemlayout Address : R.layout.sample_item 이들어오는 곳이다. 리소스 디렉토리내의 주소를 가져오므로 int로 저장된다.
//     items  =데이터 꾸러미를 가져온다.

    private String photoURL;

    Context context = null;
    LayoutInflater layoutInflater = null;
    List<GetOfficialChallengeDto> getOfficialChallengeList;

    public OfficialChallengeAdapter(Context context, List<GetOfficialChallengeDto> data)
//            throws java.text.ParseException
    {
        context = context;
        getOfficialChallengeList = data;
        layoutInflater = LayoutInflater.from(context);
    }

    //데이터의 숫자를 리턴 해줄 때
    //@return
    @Override
    public int getCount() {
//        if(items == null) return 0; else
        return getOfficialChallengeList.size();
    }

    //선택된 데이터를 리턴 할 때
    //position = 위치에 따른 데이터
    //@return = position에 해당되는 data를 리턴
    @Override
    public Object getItem(int position) {
        return getOfficialChallengeList.get(position);
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
    public View getView(int position, View converView, ViewGroup parent){
            View view = layoutInflater.inflate(R.layout.item_official_challenge, null);

        TextView tv_official_challenge_title = (TextView) view.findViewById(R.id.tv_official_challenge_title);
        TextView tv_official_challenge_end_date = (TextView)  view.findViewById(R.id.tv_official_challenge_end_date);
        TextView tv_official_challenge_start_date = (TextView) view.findViewById(R.id.tv_official_challenge_start_date);
        TextView tv_official_challenge_summary = (TextView) view.findViewById(R.id.tv_official_challenge_summary);
        TextView tv_official_challenge_current = (TextView) view.findViewById(R.id.tv_official_challenge_current);
        TextView tv_official_challenge_point = (TextView) view.findViewById(R.id.tv_official_challenge_point);

        ImageView iv_ing = (ImageView) view.findViewById(R.id.iv_official_challenge_ing);
        ImageView iv_uning = (ImageView) view.findViewById(R.id.iv_official_challenge_uning);
        ImageView iv_profilePhoto = (ImageView) view.findViewById(R.id.iv_profilePhoto);

        tv_official_challenge_title.setText(getOfficialChallengeList.get(position).getTitle());

        //String으로 받은 시작날짜 Data를 포맷하여 다시 String으로 띄워주기
        LocalDateTime startDate = LocalDateTime.parse(getOfficialChallengeList.get(position).getStartDate());
        String startDate_String = startDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 부터"));
        tv_official_challenge_start_date.setText(startDate_String.toString());

        //String으로 받은 종료날짜 Data를 포맷하여 다시 String으로 띄워주기
        LocalDateTime endDate = LocalDateTime.parse(getOfficialChallengeList.get(position).getEndDate());
        String endDate_String = endDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 까지"));
        tv_official_challenge_end_date.setText(endDate_String);

        tv_official_challenge_current.setText(getOfficialChallengeList.get(position).getCurrent().toString());

        tv_official_challenge_summary.setText(getOfficialChallengeList.get(position).getSummary());
        tv_official_challenge_point.setText(getOfficialChallengeList.get(position).getPoint().toString());

        photoURL = getOfficialChallengeList.get(position).getImg();
//        System.out.println("공식 챌린지 어댑터: 프로필 사진 URI는 "+photoURL+"입니다.");
        Glide.with(parent).load(photoURL).into(iv_profilePhoto);

        Boolean isJoin = getOfficialChallengeList.get(position).getIsJoin();
        System.out.println(getOfficialChallengeList.get(position));
//        System.out.println("불린 값은 "+ isJoin + "입니다");

        if(isJoin == false) {
            iv_ing.setVisibility(view.INVISIBLE);
        }else {
            iv_uning.setVisibility(view.INVISIBLE);
        }
        return view;
        }
}
