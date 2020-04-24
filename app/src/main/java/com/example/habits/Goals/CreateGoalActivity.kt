package com.example.habits.Goals

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.Goals.FragmentGoals.Companion.goalList
import com.example.habits.R
import kotlinx.android.synthetic.main.create_goal.*
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class CreateGoalActivity() : AppCompatActivity() {
    private var color: String = ""
    // Filename for the file where the goalList is saved
    private val FILE_NAME: String = "GoalListData.txt"

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

            uncheckColors(ZielFarbeBlau, ZielFarbeRot, ZielFarbeOrange, ZielFarbeGrau)
            checkColor(ZielFarbeStandard)
        }
        ZielFarbeBlau.setOnClickListener {
            color = "Blau"

            uncheckColors(ZielFarbeStandard, ZielFarbeRot, ZielFarbeOrange, ZielFarbeGrau)
            checkColor(ZielFarbeBlau)
        }
        ZielFarbeRot.setOnClickListener {
            color = "Rot"

            uncheckColors(ZielFarbeBlau, ZielFarbeStandard, ZielFarbeOrange, ZielFarbeGrau)
            checkColor(ZielFarbeRot)
        }
        ZielFarbeOrange.setOnClickListener {
            color = "Orange"

            uncheckColors(ZielFarbeBlau, ZielFarbeRot, ZielFarbeStandard, ZielFarbeGrau)
            checkColor(ZielFarbeOrange)
        }
        ZielFarbeGrau.setOnClickListener {
            color = "Grau"

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
            if (goalName.trim().isNotEmpty() && goalDuration.trim().isNotEmpty() && goalReminder != -1) {
                val goalReminderName: RadioButton = findViewById(goalReminder)
                Toast.makeText(applicationContext, "ZielName: $goalName \nZielDauer: $goalDuration \nZielErinnerung: ${goalReminderName.text} \nZielKategorie: $goalCategory \nZielFarbe: $goalColor", Toast.LENGTH_SHORT).show()

                // Adds the user input from create_goal into goalList to display it in the "Ziele" Tab
                goalList.add(Goal(goalName, goalDuration, (goalReminderName.text.toString()), goalCategory, goalColor))

                // Saves the goalList into app-storage
                saveGoals(goalList)
                Log.i("FragmentGoals", "saveList() called: ${goalList}")

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

    // Function to save the goalList into SharedPreferences
    private fun saveGoals(goalList: ArrayList<Goal>) {
        /*val sharedPreferences = this.getSharedPreferences("goalPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(goalList)
        editor.putString("goal list", json)
        editor.apply()*/

        // TODO: funktioniert nicht
        try {
            val fos = FileOutputStream("goalList.txt")
            val oos = ObjectOutputStream(fos)
            oos.writeObject(goalList)
            oos.close()
            fos.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

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