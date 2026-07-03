package com.example.todolist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.TaskRepository
import com.example.todolist.model.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>

    init {
        val dao = AppDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(dao)
        allTasks = repository.allTasks
    }

    fun insert(task: Task) = viewModelScope.launch { repository.insert(task) }

    fun update(task: Task) = viewModelScope.launch { repository.update(task) }

    fun delete(task: Task) = viewModelScope.launch { repository.delete(task) }

    fun toggleCompleted(id: Long, completed: Boolean) = viewModelScope.launch {
        repository.toggleCompleted(id, completed)
    }
}
