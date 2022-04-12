package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


public class OfficialChallengeIng extends AppCompatActivity implements View.OnClickListener{

    Button btn_official_challenge_status, btn_official_challenge_verify;
    FrameLayout fl_official_challenge_my_status, getFl_official_challenge_whole_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official_challenge_ing);

        btn_official_challenge_status = (Button) findViewById(R.id.btn_official_challenge_status);
        btn_official_challenge_verify = (Button) findViewById(R.id.btn_official_challenge_verify);
        fl_official_challenge_my_status = (FrameLayout) findViewById(R.id.fl_official_challenge_my_status);
        getFl_official_challenge_whole_status = (FrameLayout) findViewById(R.id.fl_official_challenge_whole_status);

        btn_official_challenge_status.setOnClickListener(this);
        btn_official_challenge_verify.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_official_challenge_status:
                Intent intent1 = new Intent(getApplicationContext(),OfficialChallengeStatus.class);
                startActivity(intent1);
                break;
            case R.id.btn_official_challenge_verify:
                Intent intent2 = new Intent(getApplicationContext(),OfficialChallengeVerify.class);
                startActivity(intent2);
            default:
                break;
        }

    }
}