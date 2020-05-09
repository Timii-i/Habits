package com.example.habits.Goals

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.Goals.FragmentGoals.Companion.goalList
import com.example.habits.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.edit_goal.*
import kotlinx.android.synthetic.main.edit_goal.ZielDauer
import kotlinx.android.synthetic.main.edit_goal.ZielDauerAnzeige
import kotlinx.android.synthetic.main.edit_goal.ZielDauerEingabeClick
import kotlinx.android.synthetic.main.edit_goal.ZielErinnerung
import kotlinx.android.synthetic.main.edit_goal.ZielErinnerungRadioGroup
import kotlinx.android.synthetic.main.edit_goal.ZielName
import kotlinx.android.synthetic.main.edit_goal.ZielNameEingabe
import java.text.SimpleDateFormat
import java.util.*

class EditGoalActivity() : AppCompatActivity() {
    private var color: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_goal)

        // Sets the individual text fields to the content of the goal from that position
        setFields()

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
        ZielFarbeÄndernStandard.setOnClickListener {
            color = getString(R.string.goal_color_standard)

            uncheckColors(ZielFarbeÄndernBlau, ZielFarbeÄndernRot, ZielFarbeÄndernOrange, ZielFarbeÄndernGrau)
            checkColor(ZielFarbeÄndernStandard)
        }
        ZielFarbeÄndernBlau.setOnClickListener {
            color = getString(R.string.goal_color_blue)

            uncheckColors(ZielFarbeÄndernStandard, ZielFarbeÄndernRot, ZielFarbeÄndernOrange, ZielFarbeÄndernGrau)
            checkColor(ZielFarbeÄndernBlau)
        }
        ZielFarbeÄndernRot.setOnClickListener {
            color = getString(R.string.goal_color_red)

            uncheckColors(ZielFarbeÄndernBlau, ZielFarbeÄndernStandard, ZielFarbeÄndernOrange, ZielFarbeÄndernGrau)
            checkColor(ZielFarbeÄndernRot)
        }
        ZielFarbeÄndernOrange.setOnClickListener {
            color = getString(R.string.goal_color_orange)

            uncheckColors(ZielFarbeÄndernBlau, ZielFarbeÄndernRot, ZielFarbeÄndernStandard, ZielFarbeÄndernGrau)
            checkColor(ZielFarbeÄndernOrange)
        }
        ZielFarbeÄndernGrau.setOnClickListener {
            color = getString(R.string.goal_color_gray)

            uncheckColors(ZielFarbeÄndernBlau, ZielFarbeÄndernRot, ZielFarbeÄndernOrange, ZielFarbeÄndernStandard)
            checkColor(ZielFarbeÄndernGrau)
        }

        // Action when "Ändern" button is pressed
        ZielÄndernClick.setOnClickListener {

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

                FragmentGoals.goalList[position] =
                    Goal(
                        goalName,
                        goalDuration,
                        goalReminderName.text.toString(),
                        goalCategory,
                        goalColor
                    )

                // Edits goal in sharedPreferencs
                editSavedGoal()

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

    private fun setFields() {
        ZielNameEingabe.setText(goalList[position].Name)
        ZielDauerAnzeige.setText(goalList[position].Duration)
        when(goalList[position].Reminder) {
            getString(R.string.goal_reminder_none) -> ZielErinnerungRadioGroup.check(2131230744)
            getString(R.string.goal_reminder_daily) -> ZielErinnerungRadioGroup.check(2131230746)
            getString(R.string.goal_reminder_weekly) -> ZielErinnerungRadioGroup.check(2131230747)
            getString(R.string.goal_reminder_monthly) -> ZielErinnerungRadioGroup.check(2131230745)
            else -> ZielErinnerungRadioGroup.check(2131230744)
        }
        ZielKategorieEingabe.setText(goalList[position].Category)
        when(goalList[position].Color) {

            getString(R.string.goal_color_standard) -> {checkColor(ZielFarbeÄndernStandard)
                color = getString(R.string.goal_color_standard)}

            getString(R.string.goal_color_blue) -> {checkColor(ZielFarbeÄndernBlau)
                color = getString(R.string.goal_color_blue)}

            getString(R.string.goal_color_red) -> {checkColor(ZielFarbeÄndernRot)
                color = getString(R.string.goal_color_red)}

            getString(R.string.goal_color_orange) -> {checkColor(ZielFarbeÄndernOrange)
                color = getString(R.string.goal_color_orange)}

            getString(R.string.goal_color_gray) -> {checkColor(ZielFarbeÄndernGrau)
                color = getString(R.string.goal_color_gray)}

            else -> {checkColor(ZielFarbeÄndernStandard)
                color = ""}
        }
    }

    // Function to edit/delete the goalList into SharedPreferences
    private fun editSavedGoal() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(getString(R.string.goal_preferences_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(FragmentGoals.goalList)
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

    companion object {
        var position: Int = 0
    }
}