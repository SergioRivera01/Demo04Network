package com.sergiorivera.demo04network.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiorivera.demo04network.databinding.ItemRepositoryBinding
import com.sergiorivera.demo04network.model.Repository

class RepositoryAdapter(private val onRepositoryClicked: (Repository) -> Unit) :
    ListAdapter<Repository, RepositoryAdapter.ViewHolder>(RepositoryItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemRepositoryBinding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = getItem(position)
        holder.binding.tvRepoName.text = repository.name
        holder.binding.root.setOnClickListener{ onRepositoryClicked(repository)}
    }

    inner class ViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root)



}

class RepositoryItemCallBack : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }
}