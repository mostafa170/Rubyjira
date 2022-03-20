package com.devartlab.rubyjira.app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devartlab.rubyjira.databinding.ItemUpcomingTaskBinding
import com.devartlab.rubyjira.domain.entities.tasks.UpcomingEntities

class UpcomingTaskAdapter (private val onItemClickListener: OnUpcomingTaskClickListener):
ListAdapter<UpcomingEntities, UpcomingTaskAdapter.ViewHolder>(UpcomingTaskTypeDiffCallback()){

    class ViewHolder(val binding: ItemUpcomingTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            upcoming: UpcomingEntities,
            onClickListener: OnUpcomingTaskClickListener
        ) {
            binding.tasks = upcoming
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUpcomingTaskBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position),onItemClickListener)

}


class UpcomingTaskTypeDiffCallback : DiffUtil.ItemCallback<UpcomingEntities>() {
    override fun areItemsTheSame(oldItem: UpcomingEntities, newItem: UpcomingEntities): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UpcomingEntities, newItem: UpcomingEntities): Boolean {
        return oldItem.id == newItem.id
    }
}
class OnUpcomingTaskClickListener(val clickListener: (index: UpcomingEntities?) -> Unit) {
    fun onClick(index: UpcomingEntities?) = clickListener(index)
}