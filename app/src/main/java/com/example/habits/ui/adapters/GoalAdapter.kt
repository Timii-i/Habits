package com.example.habits.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.*
import com.example.habits.Goals.EditGoalActivity
import com.example.habits.Goals.FragmentGoals.Companion.goalList
import com.example.habits.Goals.Goal
import kotlinx.android.synthetic.main.goal_list_item.view.*

class GoalAdapter (private val items: ArrayList<Goal>, private val context: Context?): RecyclerView.Adapter<GoalViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(LayoutInflater.from(context).inflate(R.layout.goal_list_item, parent,false))
    }

    // Binds each goal in the ArrayList to a view
    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        // Sets the Name, Duration and Reminder of each element in the RecyclerView
        holder.tvGoalName.text = items[position].Name
        holder.tvGoalDuration.text = "Bis wann: ${items[position].Duration}"
        if (items[position].Reminder == "Garnicht") {
            holder.tvGoalReminder.text = "Keine Erinnerungen"
        } else {
            holder.tvGoalReminder.text = "Erinnerungen immer: ${items[position].Reminder}"
        }
        setDeleteButton(holder, position)
        setEditButton(holder, position)
    }

    // Return the number of goals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Function to set up the delete Button
    private fun setDeleteButton(holder: GoalViewHolder, position: Int) {
        holder.btnDelete.setOnClickListener{
            // Opens an AlertBox when the delete button is pressed
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle(R.string.dialog_delete_goal_title)
            alertDialog.setMessage(R.string.dialog_delete_goal_confirmation)

            // If the user presses "OK" the goal gets deleted
            alertDialog.setPositiveButton("OK", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    goalList.removeAt(position)
                    notifyDataSetChanged()
                }
            })

            // If the user presses "Abbrechen" the AlertBox closes and nothing happens
            alertDialog.setNegativeButton("Abbrechen", null)

            alertDialog.show()
        }
    }

    // Function to set up the edit button and open EditGoalActivity when edit button is pressed
    private fun setEditButton(holder: GoalViewHolder, position: Int) {
        holder.btnUpdate.setOnClickListener {
            EditGoalActivity.position = position
            context?.startActivity(Intent(context, EditGoalActivity::class.java))
        }
    }
}

class GoalViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each goal
    val tvGoalName: TextView = view.textGoalName
    val tvGoalDuration: TextView = view.textGoalDuration
    val tvGoalReminder: TextView = view.textGoalReminder
    val btnDelete: ImageButton = view.findViewById(R.id.buttonDelete)
    val btnUpdate: ImageButton = view.findViewById(R.id.buttonUpdate)
}