package com.example.habits.Goals

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.R
import com.example.habits.ui.adapters.GoalAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_goals.*
import kotlinx.android.synthetic.main.fragment_goals.view.*
import java.lang.reflect.Type
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class FragmentGoals : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Calls the function to load the goals if there are any
        val sharedPreferences = this.activity!!.getSharedPreferences("goalPreferences", Context.MODE_PRIVATE)
        if(sharedPreferences.contains("goals")) {
            loadGoals()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        // Action when the "Neues Ziel" Button is pressed
        view.createGoalClick.setOnClickListener {
            startActivity(Intent(activity, CreateGoalActivity::class.java))
        }

        return view
    }

    // onStart
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        // Shows the text if no goals are currently in the goalList
        if (goalList.isNotEmpty()) {
            goalEmptyListTitle.visibility = View.INVISIBLE
            goalEmptyListText.visibility = View.INVISIBLE
        } else {
            goalEmptyListTitle.visibility = View.VISIBLE
            goalEmptyListText.visibility = View.VISIBLE
        }

        // Loads the goals into the the view
        rv_goal_list.layoutManager = LinearLayoutManager(activity)
        rv_goal_list.adapter = GoalAdapter(
            goalList,
            activity
        )
    }

    // Function to load in the saved Goals from sharedPreferences
    private fun loadGoals() {
        val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences("goalPreferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("goals", null)
        val type: Type = object: TypeToken<ArrayList<Goal>>() {}.type
        goalList = gson.fromJson(json, type)

    }

companion object {
    // The List where the goals are saved to show in the "Ziele" Tab
    var goalList: ArrayList<Goal> = ArrayList()
}

}
