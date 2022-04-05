package com.example.cz2006ver2.HomePage

data class Todo(
    val title: String,
    val deadline: String,
    val taskID: String,
    val elderUID: String,
    var completed: Boolean,
    var isChecked: Boolean = false
)