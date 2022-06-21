package com.efecanbayat.movieapplication.ui.feature.persondetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efecanbayat.movieapplication.data.model.response.persondetail.Cast
import com.efecanbayat.movieapplication.databinding.ItemCreditBinding

class PersonCreditsAdapter : ListAdapter<Cast, PersonCreditsAdapter.PersonCreditsViewHolder>(PersonCreditsDiffUtil()) {

    inner class PersonCreditsViewHolder(val binding: ItemCreditBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonCreditsViewHolder {
        return PersonCreditsViewHolder(ItemCreditBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PersonCreditsViewHolder, position: Int) {
        val item = getItem(position) as Cast
        holder.binding.item = item
        holder.binding.executePendingBindings()
    }
}

class PersonCreditsDiffUtil : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Cast,
        newItem: Cast
    ): Boolean {
        return oldItem == newItem
    }
}