package com.example.gbt_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gbt_4.adapter.InviteAdapter
import com.example.gbt_4.databinding.ActivityNoticeBinding

class Notice : AppCompatActivity() {
    private lateinit var binding : ActivityNoticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.recyclerView.layoutManager = layoutManager


        val adapter = InviteAdapter()
        adapter.invites.add(InviteNotice("설명재", "금연ㄱㄱ"))
        adapter.invites.add(InviteNotice("설명재2", "금연ㄱㄱㄱㄱㄱ"))
        adapter.invites.add(InviteNotice("설명재3", "금연ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ"))

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setItemClickListener(object: InviteAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                var intent = Intent(v.context, CustomChallengeDetail::class.java)

                startActivity(intent)
            }
        })
    }
}