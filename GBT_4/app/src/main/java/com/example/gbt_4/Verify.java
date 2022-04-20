package com.example.gbt_4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Verify extends Activity {

    Button btn_verify_back,btn_verify_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_verify);

        btn_verify_back = (Button) findViewById(R.id.btn_verify_back);

        btn_verify_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_verify_submit = (Button) findViewById(R.id.btn_verify_submit);
        btn_verify_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Verify.this, "일기작성이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}