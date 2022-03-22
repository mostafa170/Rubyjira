package com.devartlab.rubyjira.app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devartlab.rubyjira.databinding.ItemIndexTaskBinding
import com.devartlab.rubyjira.domain.entities.tasks.IndexEntities

class IndexTaskAdapter (private val onItemClickListener: OnIndexTaskClickListener):
ListAdapter<IndexEntities, IndexTaskAdapter.ViewHolder>(IndexTaskTypeDiffCallback()){
    private var selectedItem: IndexEntities? = null
    class ViewHolder(val binding: ItemIndexTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            index: IndexEntities,
            isSelected: Boolean,
            onClickListener: OnIndexTaskClickListener
        ) {
            binding.tasks = index
            binding.isSelected = isSelected
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemIndexTaskBinding.inflate(layoutInflater, parent, false)
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


class IndexTaskTypeDiffCallback : DiffUtil.ItemCallback<IndexEntities>() {
    override fun areItemsTheSame(oldItem: IndexEntities, newItem: IndexEntities): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: IndexEntities, newItem: IndexEntities): Boolean {
        return oldItem.id == newItem.id
    }
}
class OnIndexTaskClickListener(val clickListener: (index: IndexEntities?) -> Unit) {
    fun onClick(index: IndexEntities?) = clickListener(index)
}