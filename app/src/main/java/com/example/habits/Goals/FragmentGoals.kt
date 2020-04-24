package com.example.habits.Goals

import android.content.Context
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
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_goals.*
import kotlinx.android.synthetic.main.fragment_goals.view.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.ObjectInputStream


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

        loadGoals()
        //addGoals()

        // Action when the "Neues Ziel" Button is pressed
        view.createGoalClick.setOnClickListener {
            startActivity(Intent(activity, CreateGoalActivity::class.java))
        }

        return view
    }

    // onStart
    override fun onStart() {
        super.onStart()
        Log.i("FragmentGoals", "vor loadGoals(): $goalList" )
        //loadGoals()
        Log.i("FragmentGoals", "loadGoals() called: $goalList")
        //saveGoals()
        //Log.i("FragmentGoals", "saveList() called: $goalList")
        //val goalList2: ArrayList<Goal> = loadData()
        // Loads the goals into the the view
        rv_goal_list.layoutManager = LinearLayoutManager(activity)
        rv_goal_list.adapter = GoalAdapter(
            goalList,
            activity
        )
    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentGoals", "onResume() called")
    }


    // Function to save the goalList into SharedPreferences
    fun saveGoals() {
        val sharedPreferences = activity!!.getSharedPreferences("goalPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(goalList)
        editor.putString("goal list", json)
        editor.apply()
    }

    private fun loadGoals() {
        /*val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences ("goalPreferences", MODE_PRIVATE);
        val gson =  Gson();
        val json = sharedPreferences . getString ("goal list", null);
        val type = object:  TypeToken<ArrayList<Goal>>() {}.type;
        return gson.fromJson(json, type);*/
        try {
            val fis = FileInputStream("goalList.txt")
            val ois = ObjectInputStream(fis)
            goalList = ois.readObject() as ArrayList<Goal>
            ois.close()
            fis.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
            return
        } catch (c: ClassNotFoundException) {
            Log.i("tmp", "GoalList:")
            c.printStackTrace()
            return
        }
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
