package com.example.habits.Goals

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.R
import com.example.habits.ui.adapters.GoalAdapter
import kotlinx.android.synthetic.main.fragment_goals.*
import kotlinx.android.synthetic.main.fragment_goals.view.createGoalClick

/**
 * A simple [Fragment] subclass.
 */
class FragmentGoals : Fragment() {
    // To keep track if the user creates a new goal or updates an existing one
    private var isUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        addGoals()

        Log.i("FragmentGoals", "onCreateView called")

        // Action when the "Neues Ziel" Button is pressed
        view.createGoalClick.setOnClickListener {
            startActivity(Intent(activity, CreateGoalActivity::class.java))
        }

        return view
    }

    // onStart
    override fun onStart() {
        super.onStart()


        rv_goal_list.layoutManager = LinearLayoutManager(activity)
        rv_goal_list.adapter = GoalAdapter(
            goalList,
            activity
        )
    }

    companion object {

        // The List where the goals are saved to show in the "Ziele" Tab
        val goalList: ArrayList<Goal> = ArrayList()

        fun addGoals() {
            goalList.add(
                Goal(
                    "adadada",
                    "15.08.2020",
                    "Garnicht"
                )
            )
            goalList.add(
                Goal(
                    "123",
                    "69.03.2069",
                    "Täglich"
                )
            )
            goalList.add(
                Goal(
                    "xccxc",
                    "01.04.2020",
                    "Monatlich"
                )
            )
            goalList.add(
                Goal(
                    "232323232",
                    "24.06.1945",
                    "Wöchentlich"
                )
            )
            goalList.add(
                Goal(
                    "hfghfhfh",
                    "01.04.2020",
                    "Monatlich"
                )
            )
            goalList.add(
                Goal(
                    "werwrwrwrw",
                    "01.04.2020",
                    "Garnicht"
                )
            )
            goalList.add(
                Goal(
                    "rwr",
                    "01.04.2020",
                    "Monatlich"
                )
            )
            goalList.add(
                Goal(
                    "vnvnvnvnvn",
                    "01.04.2020",
                    "Täglich"
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
