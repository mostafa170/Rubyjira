package com.devartlab.rubyjira.app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devartlab.rubyjira.databinding.ItemCompletedTaskBinding
import com.devartlab.rubyjira.domain.entities.tasks.CompletedEntities

class CompletedTaskAdapter (private val onItemClickListener: OnCompletedTaskClickListener):
ListAdapter<CompletedEntities, CompletedTaskAdapter.ViewHolder>(CompletedTaskTypeDiffCallback()){

    class ViewHolder(val binding: ItemCompletedTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            index: CompletedEntities,
            onClickListener: OnCompletedTaskClickListener
        ) {
            binding.tasks = index
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCompletedTaskBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position),onItemClickListener)

}


class CompletedTaskTypeDiffCallback : DiffUtil.ItemCallback<CompletedEntities>() {
    override fun areItemsTheSame(oldItem: CompletedEntities, newItem: CompletedEntities): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CompletedEntities, newItem: CompletedEntities): Boolean {
        return oldItem.id == newItem.id
    }
}
class OnCompletedTaskClickListener(val clickListener: (index: CompletedEntities?) -> Unit) {
    fun onClick(index: CompletedEntities?) = clickListener(index)
}