package com.example.habits.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habits.CreateGoalActivity
import com.example.habits.R
import kotlinx.android.synthetic.main.fragment_goals.*
import kotlinx.android.synthetic.main.fragment_goals.view.*
import kotlinx.android.synthetic.main.fragment_goals.view.ZielNameAnzeige


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
            startActivity(Intent(this.context, CreateGoalActivity::class.java))
        }

        // TODO getSharedPreferences noch implementieren !
        // To show the goals saved in preferences
        val data = activity!!.getSharedPreferences("data", Context.MODE_PRIVATE)
        val savedGoalName = data.getString("savedGoalName", "No goal name saved")
        // Just for debugging purposes
        d("tim", "saved goal name is: $savedGoalName")
        // Shows the name of the last added Goal
        //ZielNameAnzeige.text = savedGoalName


        return view
    }

    companion object {
        fun newInstance() = FragmentGoals()
    }

}
