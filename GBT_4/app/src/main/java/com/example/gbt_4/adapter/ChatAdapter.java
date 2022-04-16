package com.example.gbt_4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbt_4.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private ArrayList<String> data = null;


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.tv_chat_content);

        }
    }

    public ChatAdapter(ArrayList<String> list){
        data = list;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.item_chat,parent,false);
        View view2 = inflater.inflate(R.layout.item_my_chat,parent,false);

        ChatAdapter.ViewHolder viewHolder = new ChatAdapter.ViewHolder(view1);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        String text = data.get(position);
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}
