package com.example.gbt_4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gbt_4.InviteNotice
import com.example.gbt_4.R
import com.example.gbt_4.databinding.ActivityNoticeBinding
import com.example.gbt_4.databinding.InviteBinding

class InviteAdapter : RecyclerView.Adapter<InviteAdapter.ViewHolder>() {
    var invites = ArrayList<InviteNotice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = InviteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val invite = invites[position]
        holder.setItem(invite)
    }

    override fun getItemCount() = invites.size

    inner class ViewHolder(val binding: InviteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(invite: InviteNotice) {
            binding.tvCaller.text = invite.caller
            binding.tvTitle.text = invite.title
        }

    }
}