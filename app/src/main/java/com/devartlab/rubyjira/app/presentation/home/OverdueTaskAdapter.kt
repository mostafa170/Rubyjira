package com.devartlab.rubyjira.app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devartlab.rubyjira.databinding.ItemIndexTaskBinding
import com.devartlab.rubyjira.databinding.ItemOverdueTaskBinding
import com.devartlab.rubyjira.domain.entities.tasks.IndexEntities
import com.devartlab.rubyjira.domain.entities.tasks.OverdueEntities

class OverdueTaskAdapter (private val onItemClickListener: OnOverdueTaskClickListener):
ListAdapter<OverdueEntities, OverdueTaskAdapter.ViewHolder>(OverdueTaskTypeDiffCallback()){
    private var selectedItem:OverdueEntities? = null
    class ViewHolder(val binding: ItemOverdueTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            overdue: OverdueEntities,
            isSelected: Boolean,
            onClickListener: OnOverdueTaskClickListener
        ) {
            binding.tasks = overdue
            binding.isSelected = isSelected
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOverdueTaskBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), selectedItem == getItem(position), onItemClickListener)
        holder.binding.checkboxFinishTask.setOnClickListener {
            selectedItem = getItem(position)
            notifyDataSetChanged()
            holder.binding.checkboxFinishTask.isChecked = false
            onItemClickListener.clickListener(selectedItem)
        }
    }
}


class OverdueTaskTypeDiffCallback : DiffUtil.ItemCallback<OverdueEntities>() {
    override fun areItemsTheSame(oldItem: OverdueEntities, newItem: OverdueEntities): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: OverdueEntities, newItem: OverdueEntities): Boolean {
        return oldItem.id == newItem.id
    }
}
class OnOverdueTaskClickListener(val clickListener: (index: OverdueEntities?) -> Unit) {
    fun onClick(index: OverdueEntities?) = clickListener(index)
}