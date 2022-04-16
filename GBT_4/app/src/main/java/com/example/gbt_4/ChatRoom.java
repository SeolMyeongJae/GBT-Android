package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gbt_4.adapter.ChatAdapter;

import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);


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