package com.example.habits.Notes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.Notes.FragmentNotes.Companion.noteList
import com.example.habits.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.create_note.*
import java.util.*

class CreateNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_note)

        // Action when "Hinzufügen" button is pressed
        NotizHinzufügenClick.setOnClickListener {


            // Saves the input from the user in variables
            val noteName: String = NotizNameEingabe.text.toString()
            val noteContent: String = NotizInhaltEingabe.text.toString()


            NotizNameEingabe.error = null
            NotizInhaltEingabe.error = null


            // Checks if the input fields are empty or not
            if (noteName.trim().isNotEmpty() && noteContent.trim().isNotEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "NotizName: $noteName \nNotizInhalt: $noteContent \n",
                    Toast.LENGTH_SHORT
                ).show()

                // Adds the user input from create_note into noteList to display it in the "Notizen" Tab
                noteList.add(Note(noteName, noteContent))

                // Saves the noteList into sharedPreferences
                saveNotes()

                finish()
            } else {
                if (noteName.trim().isEmpty()) {
                    NotizNameEingabe.error = "Fehlende Eingabe"
                }
                if (noteContent.trim().isEmpty()) {
                    NotizInhaltEingabe.error = "Fehlende Eingabe"
                }
            }

            finish()
        }
    }

    // Function to save the noteList into SharedPreferences
    private fun saveNotes() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("notePreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(noteList)
        editor.putString("notes", json)
        editor.apply()

    }
}
