package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomChallengeIng extends AppCompatActivity {

    Button btn_custom_challenge_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_challenge_ing);

        btn_custom_challenge_chat.findViewById(R.id.btn_custom_challenge_chat);
        btn_custom_challenge_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}