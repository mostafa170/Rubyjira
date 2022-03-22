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
    private var selectedItem:CompletedEntities? = null
    class ViewHolder(val binding: ItemCompletedTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            index: CompletedEntities,
            isSelected: Boolean,
            onClickListener: OnCompletedTaskClickListener
        ) {
            binding.tasks = index
            binding.isSelected = isSelected
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), selectedItem == getItem(position), onItemClickListener)
        holder.binding.checkboxFinishTask.isChecked=true
        holder.binding.checkboxFinishTask.setOnClickListener {
            selectedItem = getItem(position)
            notifyDataSetChanged()
            onItemClickListener.clickListener(selectedItem)
        }
    }

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