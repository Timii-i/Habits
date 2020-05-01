package com.example.habits.Goals

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        // Set date and open calender
        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            // Format date
            val myFormat = "dd.MM.yyyy"
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
            color = "Standard"

            uncheckColors(ZielFarbeÄndernBlau, ZielFarbeÄndernRot, ZielFarbeÄndernOrange, ZielFarbeÄndernGrau)
            checkColor(ZielFarbeÄndernStandard)
        }
        ZielFarbeÄndernBlau.setOnClickListener {
            color = "Blau"

            uncheckColors(ZielFarbeÄndernStandard, ZielFarbeÄndernRot, ZielFarbeÄndernOrange, ZielFarbeÄndernGrau)
            checkColor(ZielFarbeÄndernBlau)
        }
        ZielFarbeÄndernRot.setOnClickListener {
            color = "Rot"

            uncheckColors(ZielFarbeÄndernBlau, ZielFarbeÄndernStandard, ZielFarbeÄndernOrange, ZielFarbeÄndernGrau)
            checkColor(ZielFarbeÄndernRot)
        }
        ZielFarbeÄndernOrange.setOnClickListener {
            color = "Orange"

            uncheckColors(ZielFarbeÄndernBlau, ZielFarbeÄndernRot, ZielFarbeÄndernStandard, ZielFarbeÄndernGrau)
            checkColor(ZielFarbeÄndernOrange)
        }
        ZielFarbeÄndernGrau.setOnClickListener {
            color = "Grau"

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
                Toast.makeText(applicationContext, "ZielName: $goalName \nZielDauer: $goalDuration \nZielErinnerung: ${goalReminderName.text} \nZielKategorie: $goalCategory \nZielFarbe: $goalColor", Toast.LENGTH_SHORT).show()

                FragmentGoals.goalList[position] =
                    Goal(
                        goalName,
                        goalDuration,
                        goalReminderName.text.toString(),
                        goalCategory,
                        goalColor
                    )

                // Edits/Deletes goal in sharedPreferencs
                editSavedGoals()

                finish()
            } else {
                if(goalName.trim().isEmpty()) {
                    ZielName.error = "Fehlende Eingabe"
                }
                if(goalDuration.trim().isEmpty()) {
                    ZielDauer.error = "Fehlende Eingabe"
                }
                if(goalReminder == -1) {
                    ZielErinnerung.error = "Fehlende Eingabe"
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
                    ZielNameEingabe.error = "Zu langer Name"
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
                    ZielNameEingabe.error = "Zu langer Kategoriename"
                }
            }
        })
    }

    // Function to edit/delete the goalList into SharedPreferences
    private fun editSavedGoals() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("goalPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(FragmentGoals.goalList)
        editor.putString("goals", json)
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