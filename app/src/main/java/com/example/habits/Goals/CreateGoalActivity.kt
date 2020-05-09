package com.example.habits.Goals

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.Goals.FragmentGoals.Companion.goalList
import com.example.habits.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.create_goal.*
import java.text.SimpleDateFormat
import java.util.*


class CreateGoalActivity() : AppCompatActivity() {
    private var color: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_goal)

        // Set date and open calender
        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            // Format date
            val myFormat = getString(R.string.goal_date_format)
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            // Display selected date in textView
            ZielDauerAnzeige.text = sdf.format(cal.time)

        }

        // Action when button "Datum" is pressed
        ZielDauerEingabeClick.setOnClickListener {
            // Opens the Calender
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Actions when a color is pressed
        ZielFarbeStandard.setOnClickListener {
            color = getString(R.string.goal_color_standard)

            uncheckColors(ZielFarbeBlau, ZielFarbeRot, ZielFarbeOrange, ZielFarbeGrau)
            checkColor(ZielFarbeStandard)
        }
        ZielFarbeBlau.setOnClickListener {
            color = getString(R.string.goal_color_blue)

            uncheckColors(ZielFarbeStandard, ZielFarbeRot, ZielFarbeOrange, ZielFarbeGrau)
            checkColor(ZielFarbeBlau)
        }
        ZielFarbeRot.setOnClickListener {
            color = getString(R.string.goal_color_red)

            uncheckColors(ZielFarbeBlau, ZielFarbeStandard, ZielFarbeOrange, ZielFarbeGrau)
            checkColor(ZielFarbeRot)
        }
        ZielFarbeOrange.setOnClickListener {
            color = getString(R.string.goal_color_orange)

            uncheckColors(ZielFarbeBlau, ZielFarbeRot, ZielFarbeStandard, ZielFarbeGrau)
            checkColor(ZielFarbeOrange)
        }
        ZielFarbeGrau.setOnClickListener {
            color = getString(R.string.goal_color_gray)

            uncheckColors(ZielFarbeBlau, ZielFarbeRot, ZielFarbeOrange, ZielFarbeStandard)
            checkColor(ZielFarbeGrau)
        }

        // Action when "Hinzufügen" button is pressed
        ZielHinzufügenClick.setOnClickListener {

            // Saves the input from the user in variables
            val goalName: String = ZielNameEingabe.text.toString()
            val goalDuration: String = ZielDauerAnzeige.text.toString()
            val goalReminder: Int = ZielErinnerungRadioGroup.checkedRadioButtonId
            val goalCategory: String = ZielKategorieEingabe.text.toString()
            var goalColor: String = ""
            if (color != "") {
                goalColor = color
            }

            ZielName.error = null
            ZielDauer.error = null
            ZielErinnerung.error = null

            // Checks if the input fields are empty or not
            if (goalName.trim().isNotEmpty() && goalDuration.trim().isNotEmpty() && goalReminder != -1 && goalName.trim().length <= 35 && goalCategory.trim().length <= 15) {
                val goalReminderName: RadioButton = findViewById(goalReminder)

                // Adds the user input from create_goal into goalList to display it in the "Ziele" Tab
                goalList.add(Goal(goalName, goalDuration, (goalReminderName.text.toString()), goalCategory, goalColor))

                // Saves the goalList into sharedPreferences
                saveGoals()

                finish()
            } else {
                if(goalName.trim().isEmpty()) {
                    ZielName.error = getString(R.string.missing_input)
                }
                if(goalDuration.trim().isEmpty()) {
                    ZielDauer.error = getString(R.string.missing_input)
                }
                if(goalReminder == -1) {
                    ZielErinnerung.error = getString(R.string.missing_input)
                }
            }
        }

        // Action when the goal name is longer than 35 characters
        ZielNameEingabe.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(zielNameText: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (zielNameText.trim().length > 35) {
                    ZielNameEingabe.error = getString(R.string.lengthy_name)
                }
            }
        })

        // Action when the goal category is longer than 15 characters
        ZielKategorieEingabe.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(zielKategorieText: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (zielKategorieText.trim().length > 15) {
                    ZielNameEingabe.error = getString(R.string.lengthy_category)
                }
            }
        })
    }

    // Function to save the goalList into SharedPreferences
    private fun saveGoals() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(getString(
            R.string.goal_preferences_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(goalList)
        editor.putString(getString(R.string.goals_key), json)
        editor.apply()

    }

    // Function to uncheck every other color
    private fun uncheckColors(button1: Button, button2: Button, button3: Button, button4: Button) {
        button1.text = ""
        button2.text = ""
        button3.text = ""
        button4.text = ""
    }

    // Function to check the color that was pressed
    private fun checkColor(button: Button) {
        button.text = "✔"
    }
}