package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.ItemOneBinding

class OneAdapter :
    ListAdapter<One, OneAdapter.ViewHolder>(OneDiffCallback()) {

    private val skillsAdapter = SkillsAdapter().apply {
        submitList(
            listOf(
                Skills("Android"),
                Skills("Java"),
                Skills("Kotlin"),
                Skills("RxJava"),
                Skills("RxJava"),
                Skills("RxAndroid")
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemOneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemOneBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: One) = with(binding) {
            val context = itemView.context
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = skillsAdapter
            }
            title.text = item.title
        }
    }

    class OneDiffCallback : DiffUtil.ItemCallback<One>() {

        override fun areItemsTheSame(
            oldItem: One,
            newItem: One
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: One,
            newItem: One
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}

data class One(
    val title: String
)
