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

        // Calls the function to load the notes
        loadNotes()
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

        rv_note_list.layoutManager = LinearLayoutManager(activity)
        rv_note_list.adapter = NoteAdapter(
            noteList,
            activity
        )
    }

    // Function to load in the saved Notes from sharedPreferences
    private fun loadNotes() {
        val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences("notePreferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("notes", null)
        val type: Type = object: TypeToken<ArrayList<Note>>() {}.type
        noteList = gson.fromJson(json, type)

    }

    companion object {

        // The List where the Notes are saved to show in the "Notizen" Tab
        var noteList: ArrayList<Note> = ArrayList()

        fun addNotes() {
            noteList.add(
                Note(
                    "adadada",
                    "Garnicht"
                )
            )
            noteList.add(
                Note(
                    "123",
                    "Täglich"
                )
            )
            noteList.add(
                Note(
                    "xccxc",
                    "Monatlich"
                )
            )
            noteList.add(
                Note(
                    "232323232",
                    "Wöchentlich"
                )
            )
            noteList.add(
                Note(
                    "hfghfhfh",
                    "Monatlich"
                )
            )
            noteList.add(
                Note(
                    "werwrwrwrw",
                    "Garnicht"
                )
            )
            noteList.add(
                Note(
                    "rwr",
                    "Monatlich"
                )
            )
            noteList.add(
                Note(
                    "vnvnvnvnvn",
                    "Täglich"
                )
            )
        }

        fun newInstance() = FragmentNotes()
    }
}


