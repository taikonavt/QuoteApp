package com.example.coreapi.permission

import androidx.recyclerview.widget.RecyclerView
import com.example.coreapi.databinding.ViewPermissionItemBinding

class PermissionVh(private val binding: ViewPermissionItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PermissionInformation) {
        with(binding) {
            itemTv.text = item.label
            itemTv.setCompoundDrawablesRelativeWithIntrinsicBounds(item.icon, null, null, null)
        }
    }

}
