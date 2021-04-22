package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.ItemSkillsBinding

class SkillsAdapter :
    ListAdapter<Skills, SkillsAdapter.ViewHolder>(SkillsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSkillsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemSkillsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Skills) = with(binding) {
            chip.text = item.title
        }
    }

    class SkillsDiffCallback : DiffUtil.ItemCallback<Skills>() {

        override fun areItemsTheSame(
            oldItem: Skills,
            newItem: Skills
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: Skills,
            newItem: Skills
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}

data class Skills(
    val title: String
)
