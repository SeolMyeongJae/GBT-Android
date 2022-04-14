package com.example.gbt_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gbt_4.R;
import com.example.gbt_4.dto.GetCustomChallengeDto;
import com.example.gbt_4.dto.GetOfficialChallengeDto;

import java.text.SimpleDateFormat;
import java.util.List;

public class CustomChallengeAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<GetCustomChallengeDto> getCustomChallengeList;
    String photoURL;


    @Override
    public int getCount() {
        return getCustomChallengeList.size();
    }

    @Override
    public Object getItem(int posistion) {
        return getCustomChallengeList.get(posistion);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_custom_challenge, null);

        TextView tv_custom_challenge_title = (TextView) view.findViewById(R.id.tv_custom_challenge_title);
        TextView tv_custom_challenge_endDate = (TextView) view.findViewById(R.id.tv_custom_challenge_end_date);
        TextView tv_custom_challenge_summary = (TextView) view.findViewById(R.id.tv_custom_challenge_summary);
        TextView tv_custom_challenge_memberCount = (TextView) view.findViewById(R.id.tv_custom_challenge_current);

        ImageView iv_custom_challenge_ing = (ImageView) view.findViewById(R.id.iv_custom_challenge_ing);
        ImageView iv_custom_challenge_uning = (ImageView) view.findViewById(R.id.iv_custom_challenge_uning);
        ImageView iv_custom_challenge_profilePhoto = (ImageView) view.findViewById(R.id.iv_custom_challenge_photo);

        tv_custom_challenge_title.setText(getCustomChallengeList.get(position).getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String endDate = simpleDateFormat.format(getCustomChallengeList.get(position).getEndDate());
        tv_custom_challenge_endDate.setText(endDate);
        tv_custom_challenge_summary.setText(getCustomChallengeList.get(position).getSummary());
        tv_custom_challenge_memberCount.setText(""+getCustomChallengeList.get(position).getMax());

        photoURL = getCustomChallengeList.get(position).getImg();
        Glide.with(parent).load(photoURL).into(iv_custom_challenge_profilePhoto);

        Boolean isJoin = getCustomChallengeList.get(position).getIsJoin();
        if(isJoin == false) {
            iv_custom_challenge_ing.setVisibility(view.INVISIBLE);
        }else {
            iv_custom_challenge_uning.setVisibility(view.INVISIBLE);
        }
        return view;
    }
}

