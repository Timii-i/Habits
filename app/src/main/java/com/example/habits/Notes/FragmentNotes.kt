package com.example.habits.Notes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.R
import com.example.habits.ui.adapters.NoteAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.createNoteClick
import java.lang.reflect.Type

/**
 * A simple [Fragment] subclass.
 */
class FragmentNotes : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Calls the function to load the notes if there are any
        val sharedPreferences = this.activity!!.getSharedPreferences(getString(R.string.note_preferences_name), Context.MODE_PRIVATE)
        if(sharedPreferences.contains(getString(R.string.notes_key))) {
            loadNotes()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        super.onCreateView(inflater, container, savedInstanceState)

        // Action when the "Neue Notiz" Button is pressed
        view.createNoteClick.setOnClickListener {
            startActivity(Intent(activity, CreateNoteActivity::class.java))
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        // Shows the text if no notes are currently in the noteList
        if (noteList.isNotEmpty()) {
            noteEmptyListTitle.visibility = View.INVISIBLE
            noteEmptyListText.visibility = View.INVISIBLE
        } else {
            noteEmptyListTitle.visibility = View.VISIBLE
            noteEmptyListText.visibility = View.VISIBLE
        }

        rv_note_list.layoutManager = LinearLayoutManager(activity)
        rv_note_list.adapter = NoteAdapter(
            noteList,
            activity
        )
    }

    // Function to load in the saved Notes from sharedPreferences
    private fun loadNotes() {
        val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences(getString(R.string.note_preferences_name), Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(getString(R.string.notes_key), null)
        val type: Type = object: TypeToken<ArrayList<Note>>() {}.type
        noteList = gson.fromJson(json, type)

    }

    companion object {

        // The List where the Notes are saved to show in the "Notizen" Tab
        var noteList: ArrayList<Note> = ArrayList()


        fun newInstance() = FragmentNotes()
    }
}


