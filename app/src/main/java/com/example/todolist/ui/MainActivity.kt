package com.example.todolist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.Task
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        adapter = TaskAdapter(
            onToggle = { viewModel.update(it) },
            onDelete = { viewModel.delete(it) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allTasks.observe(this) { tasks ->
            adapter.submitList(tasks)
            binding.tvEmpty.visibility = if (tasks.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }

        binding.fabAdd.setOnClickListener { showAddDialog() }
    }

    private fun showAddDialog() {
        val input = EditText(this).apply { hint = "Название задачи" }
        MaterialAlertDialogBuilder(this)
            .setTitle("Новая задача")
            .setView(input)
            .setPositiveButton("Добавить") { _, _ ->
                val title = input.text.toString().trim()
                if (title.isNotEmpty()) viewModel.insert(Task(title = title))
                else Toast.makeText(this, "Введите название", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}
