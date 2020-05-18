package com.example.habits.Goals

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.habits.Goals.FragmentGoals.Companion.goalList
import com.example.habits.MainActivity
import com.example.habits.Notification.NotificationReceiver
import com.example.habits.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.create_goal.*
import java.text.SimpleDateFormat
import java.util.*


class CreateGoalActivity() : AppCompatActivity() {
    private var color: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
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
            if (goalName.trim().isNotEmpty() && goalDuration.trim().isNotEmpty() && goalReminder != -1 && goalName.trim().length <= 35 && goalCategory.trim().length <= 15) {
                val goalReminderName: RadioButton = findViewById(goalReminder)
                val goalReminderNameString: String = goalReminderName.text.toString()
                //Toast.makeText(applicationContext, "ZielName: $goalName \nZielDauer: $goalDuration \nZielErinnerung: ${goalReminderName.text} \nZielKategorie: $goalCategory \nZielFarbe: $goalColor", Toast.LENGTH_SHORT).show()

                // Generates a pseudo random ID for each goal (returns an int between 0 and 999999 (including))
                val goalId: Int = (0..999999).random()

                // Adds the user input from create_goal into goalList to display it in the "Ziele" Tab
                goalList.add(
                    Goal(
                        goalName,
                        goalDuration,
                        goalReminderNameString,
                        goalCategory,
                        goalColor,
                        goalId
                    ))

                // Sets a new Notification if any Reminder but "Garnicht" is clicked
                if (goalReminderNameString != "Garnicht") {
                    startAlarm(goalReminderNameString, goalId)
                }

                // Saves the goalList into sharedPreferences
                saveGoals()

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
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(zielNameText: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (zielNameText.trim().length > 35) {
                    ZielNameEingabe.error = "Zu langer Name"
                }
            }
        })

        // Action when the goal category is longer than 15 characters
        ZielKategorieEingabe.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(zielKategorieText: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (zielKategorieText.trim().length > 15) {
                    ZielKategorieEingabe.error = "Zu langer Kategoriename"
                }
            }
        })
    }

    // Function to create an alarm
    private fun startAlarm(reminder: String, id: Int) {
        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent: Intent = Intent(this, NotificationReceiver::class.java)
        // requestCode muss unique sein für jeden Pendingintent
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, id, intent, 0)

        // Set repeating Alarm
        when(reminder) {

            // Daily alarm
            "Täglich" -> {
                val calendar: Calendar = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, 1)
                }

                Log.i("tim", "triggerat: ${Date()}")
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
            }

            // Weekly alarm
            "Wöchentlich" -> {
                val calendar: Calendar = Calendar.getInstance().apply {
                    add(Calendar.WEEK_OF_YEAR, 1)
                }

                // Have to use setRepeating instead of setInexactRepeating because we can't use any of the constants for the interval
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, calendar.timeInMillis, pendingIntent)
            }

            // Monthly alarm
            "Monatlich" -> {
                val calendar: Calendar = Calendar.getInstance().apply {
                    add(Calendar.MONTH, 1)
                }

                // Have to use setRepeating instead of setInexactRepeating because we can't use any of the constants for the interval
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, calendar.timeInMillis, pendingIntent)
            }
        }
    }

    // Function to save the goalList into SharedPreferences
    private fun saveGoals() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("goalPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(goalList)
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
}