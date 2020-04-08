package com.example.habits

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        /*// To show the Goals saved in preferences
        val data = getSharedPreferences("data", Context.MODE_PRIVATE)
        val savedGoalName = data.getString("savedGoalName", "No Goal name saved")
        // Just for debugging purposes
        d("tim", "saved goal name is: $savedGoalName")

        // Shows the name of the last added Goal
        ZielAnzeige.text = savedGoalName*/

        // Action when the "Neues Ziel" Button ist pressd
        CreateGoalClick.setOnClickListener {
            startActivity(Intent(this, CreateGoalActivity::class.java))
        }
    }
}