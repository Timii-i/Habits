package com.example.habits.Goals

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.MainActivity
import com.example.habits.Notification.ReminderBroadcast
import com.example.habits.R
import com.example.habits.ui.adapters.GoalAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_goals.*
import kotlinx.android.synthetic.main.fragment_goals.view.*
import java.lang.reflect.Type
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class FragmentGoals : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //createNotificationChannel()

        // Calls the function to load the goals if there are any
        val sharedPreferences = this.activity!!.getSharedPreferences("goalPreferences", Context.MODE_PRIVATE)
        if(sharedPreferences.contains("goals")) {
            loadGoals()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        // Action when the "Neues Ziel" Button is pressed
        view.createGoalClick.setOnClickListener {
            startActivity(Intent(activity, CreateGoalActivity::class.java))
        }

        return view
    }

    // onStart
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        // Shows the text if no goals are currently in the goalList
        if (goalList.isNotEmpty()) {
            goalEmptyListTitle.visibility = View.INVISIBLE
            goalEmptyListText.visibility = View.INVISIBLE
        } else {
            goalEmptyListTitle.visibility = View.VISIBLE
            goalEmptyListText.visibility = View.VISIBLE
        }

        // Loads the goals into the the view
        rv_goal_list.layoutManager = LinearLayoutManager(activity)
        rv_goal_list.adapter = GoalAdapter(
            goalList,
            activity
        )

        //alarmManager.setR(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, pendingIntent )
        // Sets the notification to goalReminder

        /*val intent: Intent = Intent(activity, ReminderBroadcast::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val alarmManager: AlarmManager = activity!!.getSystemService(ALARM_SERVICE) as AlarmManager

        val zonedToday = ZonedDateTime.now(ZoneId.of("Europe/Berlin")).toLocalDate()
        val zonedFormattedToday = zonedToday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
        Log.i("tim", "Zeit heute: $zonedFormattedToday")
        goalList.forEach lit@{
            if(it.Reminder == "Täglich") {
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10 * 10, pendingIntent)
                //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_DAY, AlarmManager.INTERVAL_DAY, pendingIntent)
            }
            else if(it.Reminder == "Wöchentlich") {
                val calendar: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.DAY_OF_WEEK, 1)
                }
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
            }
            else if(it.Reminder == "Monatlich") {
                val calendar: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.DAY_OF_MONTH, 1)
                }
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
            }
            else {
                // continue
                return@lit
            }
        }*/
    }

    /*// Create the Notification channel for notifications
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "GoalReminderChannel"
            val description = "Channel for Goal Reminder"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel = NotificationChannel("goalNotify", name, importance)
            channel.description = description

            val notificationManager: NotificationManager = activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }*/

    // Function to load in the saved Goals from sharedPreferences
    private fun loadGoals() {
        val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences("goalPreferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("goals", null)
        val type: Type = object: TypeToken<ArrayList<Goal>>() {}.type
        goalList = gson.fromJson(json, type)

    }

companion object {
    // The List where the goals are saved to show in the "Ziele" Tab
    var goalList: ArrayList<Goal> = ArrayList()
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
