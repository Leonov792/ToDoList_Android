package com.example.todolist.data

import androidx.lifecycle.LiveData
import com.example.todolist.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun update(task: Task) = taskDao.update(task)
    suspend fun delete(task: Task) = taskDao.delete(task)
    suspend fun toggleCompleted(id: Long, completed: Boolean) = taskDao.toggleCompleted(id, completed)
}
