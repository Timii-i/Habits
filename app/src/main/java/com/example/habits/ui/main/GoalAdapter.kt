package com.example.habits.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.Goal
import com.example.habits.R
import kotlinx.android.synthetic.main.goal_list_item.view.*

class GoalAdapter (private val items: ArrayList<Goal>, private val context: Context?): RecyclerView.Adapter<ViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.goal_list_item, parent,false))
    }

    // Return the number of goals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Binds each goal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvGoalName.text = items[position].Name
        holder.tvGoalDuration.text = items[position].Duration
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each goal to
    val tvGoalName: TextView = view.textGoalName
    val tvGoalDuration: TextView = view.textGoalDuration
}