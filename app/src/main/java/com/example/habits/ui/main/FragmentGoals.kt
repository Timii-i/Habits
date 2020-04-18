package com.example.habits.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.habits.CreateGoalActivity
import com.example.habits.R
import kotlinx.android.synthetic.main.fragment_goals.view.createGoalClick

/**
 * A simple [Fragment] subclass.
 */
class FragmentGoals : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        // Action when the "Neues Ziel" Button is pressed
        view.createGoalClick.setOnClickListener {
            startActivity(Intent(activity, CreateGoalActivity::class.java))
            //fragmentTransaction
        }
        return view
    }

    companion object {
        /*private const val ARG_GOALNAME = "goalname"
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
