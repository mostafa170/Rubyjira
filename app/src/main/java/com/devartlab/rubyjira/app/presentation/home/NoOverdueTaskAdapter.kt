package com.devartlab.rubyjira.app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devartlab.rubyjira.databinding.ItemIndexTaskBinding
import com.devartlab.rubyjira.databinding.ItemNoOverdueTaskBinding
import com.devartlab.rubyjira.domain.entities.tasks.IndexEntities
import com.devartlab.rubyjira.domain.entities.tasks.NoOverdueEntities

class NoOverdueTaskAdapter (private val onItemClickListener: OnNoOverdueTaskClickListener):
ListAdapter<NoOverdueEntities, NoOverdueTaskAdapter.ViewHolder>(OnNoOverdueTaskTypeDiffCallback()){
    private var selectedItem:NoOverdueEntities?=null
    class ViewHolder(val binding: ItemNoOverdueTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            index: NoOverdueEntities,
            isSelected: Boolean,
            onClickListener: OnNoOverdueTaskClickListener
        ) {
            binding.tasks = index
            binding.isSelected = isSelected
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNoOverdueTaskBinding.inflate(layoutInflater, parent, false)
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


class OnNoOverdueTaskTypeDiffCallback : DiffUtil.ItemCallback<NoOverdueEntities>() {
    override fun areItemsTheSame(oldItem: NoOverdueEntities, newItem: NoOverdueEntities): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NoOverdueEntities, newItem: NoOverdueEntities): Boolean {
        return oldItem.id == newItem.id
    }
}
class OnNoOverdueTaskClickListener(val clickListener: (index: NoOverdueEntities?) -> Unit) {
    fun onClick(index: NoOverdueEntities?) = clickListener(index)
}