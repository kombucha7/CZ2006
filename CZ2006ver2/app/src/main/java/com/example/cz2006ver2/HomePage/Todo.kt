package com.example.cz2006ver2.HomePage

data class Todo(
    val title: String,
    val deadline: String,
    val taskID: String,
    val elderUID: String,
    var isChecked: Boolean = false
)