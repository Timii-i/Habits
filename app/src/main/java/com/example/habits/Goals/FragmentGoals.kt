package com.example.habits.Goals

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.R
import com.example.habits.ui.adapters.GoalAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_goals.*
import kotlinx.android.synthetic.main.fragment_goals.view.*
import java.lang.reflect.Type


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
    override fun onStart() {
        super.onStart()

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

fun addGoals() {
    goalList.add(
        Goal(
            "adadada",
            "15.08.2020",
            "Garnicht",
            ""
        )
    )
    goalList.add(
        Goal(
            "123",
            "69.03.2069",
            "Täglich",
            "Haushalt",
            "Blau"
        )
    )
    goalList.add(
        Goal(
            "oigfnrsdig",
            "69.03.2069",
            "Monatlich",
            "ExtremlangeKategorie",
            "Rot"
        )
    )
    goalList.add(
        Goal(
            "5357547",
            "42.01.2420",
            "Garnicht",
            "Haushalt",
            "Orange"
        )
    )
    goalList.add(
        Goal(
            "Mein Ziel420",
            "77.99.1366",
            "Täglich",
            "",
            "Grau"
        )
    )
}
/*
private const val ARG_GOALNAME = "goalname"
private const val ARG_GOALDURATION = "goalduration"
private const val ARG_GOALREMINDER = "goalreminder"

fun newInstance(goalName: String, goalDuration: String, goalReminder: String) = FragmentGoals.apply() {
    var arguments = bundleOf(
        ARG_GOALNAME to goalName,
        ARG_GOALDURATION to goalDuration,
        ARG_GOALREMINDER to goalReminder
    )
}*/
fun newInstance() = FragmentGoals()
}

}
