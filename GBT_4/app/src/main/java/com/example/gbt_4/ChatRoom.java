package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gbt_4.adapter.ChatAdapter;

import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {
    Button btn_chat_room_back, btn_chat_room_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        btn_chat_room_back = (Button)findViewById(R.id.btn_chat_room_back);
        btn_chat_room_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_chat_room_submit = (Button)findViewById(R.id.btn_chat_room_submit);


        //더미데이터 리스트
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<=10;i++){
            list.add(String.format("Text %d",i));
        }

        RecyclerView recyclerView = findViewById(R.id.rv_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ChatAdapter chatAdapter = new ChatAdapter(list);
        recyclerView.setAdapter(chatAdapter);
    }
}