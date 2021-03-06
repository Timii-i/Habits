package com.example.habits.Goals

// Goal data class that holds the input data from CreateGoalActivity
data class Goal (
    val Name: String,
    val Duration: String,
    val Reminder: String,
    val Category: String = "", // Empty string to make the parameter optional
    val Color: String = "",
    val ID: Int
)