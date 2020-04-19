package com.example.habits.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.habits.CreateGoalActivity
import com.example.habits.Goal
import com.example.habits.R
import kotlinx.android.synthetic.main.fragment_goals.*
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

        Log.i("FragmentGoals", "onCreateView called")

        // Action when the "Neues Ziel" Button is pressed
        view.createGoalClick.setOnClickListener {
            startActivity(Intent(activity, CreateGoalActivity::class.java))
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FragmentGoals", "onAttach called")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentGoals", "onCreate called")
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("FragmentGoals", "onActivityCreated called")
    }

    // onStart
    override fun onStart() {
        super.onStart()
        goalList.forEach() {
            ZielAnzeige.append("${it.Name} -- ${it.Duration} -- ${it.Reminder}\n")
        }

        Log.i("FragmentGoals", "onStart called")
    }
    override fun onResume() {
        super.onResume()
        Log.i("FragmentGoals", "onResume called")
    }
    override fun onPause() {
        super.onPause()
        Log.i("FragmentGoals", "onPause called")
    }
    override fun onStop() {
        super.onStop()
        Log.i("FragmentGoals", "onStop called")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentGoals", "onDestroyView called")
    }
    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentGoals", "onDetach called")
    }

    companion object {

        // The List where the goals are saved to show in the "Ziele" Tab
        var goalList: MutableList<Goal> = ArrayList()
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
