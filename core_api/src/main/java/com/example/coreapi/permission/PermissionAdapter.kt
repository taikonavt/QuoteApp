package com.example.coreapi.permission

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coreapi.databinding.ViewPermissionItemBinding

class PermissionAdapter(private val items: List<PermissionInformation>) :
    RecyclerView.Adapter<PermissionVh>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionVh =
        PermissionVh(
            ViewPermissionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PermissionVh, position: Int) =
        holder.bind(items[position])

}
