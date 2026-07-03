package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.Task

class TaskAdapter(private val onToggle: (Task) -> Unit, private val onDelete: (Task) -> Unit) :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.binding.apply {
            tvTitle.text = task.title
            cbCompleted.isChecked = task.isCompleted
            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked != task.isCompleted) onToggle(task.copy(isCompleted = isChecked))
            }
            btnDelete.setOnClickListener { onDelete(task) }
        }
    }

    private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }
}
