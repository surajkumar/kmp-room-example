package org.example.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.example.project.database.AppDatabase
import org.example.project.database.TodoEntity

class AppViewModel(val database: AppDatabase) : ViewModel() {
    val todoDao = database.getDao()
    val totalTodos = mutableStateOf(0)
    val todos = MutableStateFlow<List<TodoEntity>>(emptyList())

    fun fetchTotalTodos() = viewModelScope.launch {
        totalTodos.value = todoDao.count()
    }

    fun fetchTodos() = viewModelScope.launch {
        todoDao.getAllAsFlow().collect { list ->
            todos.value = list
        }
    }

    fun addTodo(todoEntity: TodoEntity) = viewModelScope.launch {
        todoDao.insert(todoEntity)
    }
}