package com.example.habits.Goals

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.R
import kotlinx.android.synthetic.main.create_goal.*
import kotlinx.android.synthetic.main.create_goal.ZielDauer
import kotlinx.android.synthetic.main.create_goal.ZielDauerAnzeige
import kotlinx.android.synthetic.main.create_goal.ZielDauerEingabeClick
import kotlinx.android.synthetic.main.create_goal.ZielErinnerung
import kotlinx.android.synthetic.main.create_goal.ZielErinnerungRadioGroup
import kotlinx.android.synthetic.main.create_goal.ZielName
import kotlinx.android.synthetic.main.create_goal.ZielNameEingabe
import kotlinx.android.synthetic.main.edit_goal.*
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
        ZielFarbeStandard.setOnClickListener {
            color = "Standard"
        }
        ZielFarbeHellblau.setOnClickListener {
            color = "Hellblau"
        }
        ZielFarbeHellgrün.setOnClickListener {
            color = "Hellgrün"
        }
        ZielFarbeHellrot.setOnClickListener {
            color = "Hellrot"
        }
        ZielFarbeDunkelgrau.setOnClickListener {
            color = "Dunkelgrau"
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
            if (goalName.trim().isNotEmpty() && goalDuration.trim().isNotEmpty() && goalReminder != -1) {
                val goalReminderName: RadioButton = findViewById(goalReminder)
                Toast.makeText(applicationContext, "ZielName: $goalName \nZielDauer: $goalDuration \nZielErinnerung: ${goalReminderName.text} \nZielKategorie: $goalCategory \nZielFarbe: $goalColor", Toast.LENGTH_SHORT).show()

                // Adds the user input from create_goal into goalList to display it in the "Ziele" Tab
                FragmentGoals.goalList.add(Goal(goalName, goalDuration, (goalReminderName.text.toString()), goalCategory, goalColor))

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
    }
}