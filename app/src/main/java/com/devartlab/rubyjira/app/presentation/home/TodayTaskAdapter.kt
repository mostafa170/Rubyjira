package com.devartlab.rubyjira.app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devartlab.rubyjira.databinding.ItemTodayTaskBinding
import com.devartlab.rubyjira.domain.entities.tasks.TodayEntities

class TodayTaskAdapter (private val onItemClickListener: OnTodayTaskClickListener):
ListAdapter<TodayEntities, TodayTaskAdapter.ViewHolder>(TodayTaskTypeDiffCallback()){

    class ViewHolder(val binding: ItemTodayTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            today: TodayEntities,
            onClickListener: OnTodayTaskClickListener
        ) {
            binding.tasks = today
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTodayTaskBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position),onItemClickListener)

}

class TodayTaskTypeDiffCallback : DiffUtil.ItemCallback<TodayEntities>() {
    override fun areItemsTheSame(oldItem: TodayEntities, newItem: TodayEntities): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TodayEntities, newItem: TodayEntities): Boolean {
        return oldItem.id == newItem.id
    }
}
class OnTodayTaskClickListener(val clickListener: (index: TodayEntities?) -> Unit) {
    fun onClick(index: TodayEntities?) = clickListener(index)
}