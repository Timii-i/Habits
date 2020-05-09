package com.example.habits.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.Goals.EditGoalActivity
import com.example.habits.Goals.FragmentGoals
import com.example.habits.Goals.FragmentGoals.Companion.goalList
import com.example.habits.Goals.Goal
import com.example.habits.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.goal_list_item.view.*
import java.lang.reflect.Type


class GoalAdapter (private var items: ArrayList<Goal>, private val context: Context?): RecyclerView.Adapter<GoalViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(LayoutInflater.from(context).inflate(R.layout.goal_list_item, parent,false))
    }

    // Binds each goal in the ArrayList to a view
    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        // Sets the Name, Duration and Reminder of each element in the RecyclerView
        holder.tvGoalName.text = items[position].Name
        holder.tvGoalDuration.text = items[position].Duration
        if (items[position].Reminder == context!!.getString(R.string.goal_reminder_none)) {
            holder.tvGoalReminder.text = " - "
        } else {
            holder.tvGoalReminder.text = items[position].Reminder
        }
        if (items[position].Category == "") {
            holder.tvGoalCategory.text = ""
            holder.ivGoalCategory.visibility = View.INVISIBLE
        } else {
            holder.tvGoalCategory.text = items[position].Category
        }
        setDeleteButton(holder, position)
        setEditButton(holder, position)
        setBackground(holder, position)
    }

    // Return the number of goals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Function to set the background for each goal
    private fun setBackground(holder: GoalViewHolder, position: Int) {
        if (items[position].Color == "" || items[position].Color == context!!.getString(R.string.goal_color_standard)) {
            holder.layout.setBackgroundResource(R.drawable.button_standard)

        } else if (items[position].Color == context.getString(R.string.goal_color_blue)) {
            holder.layout.setBackgroundResource(R.drawable.button_blue)

        } else if (items[position].Color == context.getString(R.string.goal_color_red)) {
            holder.layout.setBackgroundResource(R.drawable.button_red)

        } else if (items[position].Color == context.getString(R.string.goal_color_orange)) {
            holder.layout.setBackgroundResource(R.drawable.button_orange)

        } else if (items[position].Color == context.getString(R.string.goal_color_gray)) {
            holder.layout.setBackgroundResource(R.drawable.button_gray)

        }
    }

    // Function to set up the delete Button
    private fun setDeleteButton(holder: GoalViewHolder, position: Int) {
        holder.btnDelete.setOnClickListener{
            // Opens an AlertBox when the delete button is pressed
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle(R.string.dialog_delete_goal_title)
            alertDialog.setMessage(R.string.dialog_delete_goal_confirmation)

            // If the user presses "OK" the goal gets deleted
            alertDialog.setPositiveButton(context!!.getString(R.string.dialog_alert_positive), object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    goalList.removeAt(position)
                    deleteGoal()
                    notifyDataSetChanged()
                }
            })

            // If the user presses "Abbrechen" the AlertBox closes and nothing happens
            alertDialog.setNegativeButton(context.getString(R.string.dialog_alert_negative), null)

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

    // Function to delete the element goalList and save into SharedPreferences
    private fun deleteGoal() {
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(context.getString(
                    R.string.goal_preferences_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(goalList)
        editor.putString(context.getString(R.string.goals_key), json)
        editor.apply()

    }
}


class GoalViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each goal
    val tvGoalName: TextView = view.textGoalName
    val tvGoalDuration: TextView = view.textGoalDuration
    val tvGoalReminder: TextView = view.textGoalReminder
    val tvGoalCategory: TextView = view.textGoalCategory
    val btnDelete: ImageButton = view.findViewById(R.id.buttonDelete)
    val btnUpdate: ImageButton = view.findViewById(R.id.buttonUpdate)
    val ivGoalCategory: ImageView = view.findViewById(R.id.imageGoalCategory)
    val layout: RelativeLayout = view.findViewById(R.id.goalLayout)
}