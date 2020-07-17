package com.example.habits.Motivation

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.R
import com.example.habits.ui.adapters.TipAdapter
import kotlinx.android.synthetic.main.fragment_motivation.*
import java.io.File
import java.io.InputStream

/**
 * A simple [Fragment] subclass.
 */
class FragmentMotivation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_motivation, container, false)
    super.onCreateView(inflater, container, savedInstanceState)


    return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        // Reads the tip strings from "tipList.txt" in assets
        val tipList = ArrayList(context!!.assets.open(getString(R.string.tip_list_filename)).bufferedReader().use {
            it.readLines()
        })

        tipRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        tipRecyclerView.adapter = TipAdapter(tipList)


        // Reads the motivation strings from "motivationList.txt" in assets
        val motivationList = context!!.assets.open(getString(R.string.motivation_list_filename)).bufferedReader().use {
            it.readLines()
        }.random()

        motivationText.text = motivationList
        //Log.i("tim", "File: $motivationList")

        // gets the current date as well as yesterday's date and checks if they are the same. (checks if we have a new day)
        /*
        val zonedYesterday = ZonedDateTime.now(ZoneId.of("Europe/Berlin")).minusDays(1).toLocalDate()
        val zonedFormattedYesterday = zonedYesterday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
        val DateOfToday = zonedFormattedToday

        if (DateOfToday.compareTo(zonedFormattedToday) == 0){
            ZitatText.text = "selber tag"
       }
        else{
            ZitatText.text = tempVariable
        }
         */
    }
}