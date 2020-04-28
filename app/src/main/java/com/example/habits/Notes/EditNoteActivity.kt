package com.example.habits.Notes


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.edit_note.*

class EditNoteActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_note)

        // Action when "Ändern" button is pressed
        NotizÄndernClick.setOnClickListener {

            // Saves the input from the user in variables
            val noteName: String = NotizTitelEingabe.text.toString()
            val noteContent: String = NotizInhaltEingabe.text.toString()

            NotizTitel.error = null
            NotizTitelEingabe.error = null

            // Checks if the input fields are empty or not
            if (noteName.trim().isNotEmpty() && noteContent.trim().isNotEmpty()) {
                Toast.makeText(applicationContext, "NotizName: $noteName \nNotizInhalt: $noteContent", Toast.LENGTH_SHORT).show()

                FragmentNotes.noteList[position] =
                    Note(
                        noteName,
                        noteContent
                    )

                // Edits/Deletes note in sharedPreferencs
                editSavedNotes()

                finish()
            } else {
                if(noteName.trim().isEmpty()) {
                    NotizTitel.error = "Fehlende Eingabe"
                }
                if(noteContent.trim().isEmpty()) {
                    NotizTitelEingabe.error = "Fehlende Eingabe"
                }
            }
        }
    }

    // Function to save the noteList into SharedPreferences
    private fun editSavedNotes() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("notePreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(FragmentNotes.noteList)
        editor.putString("notes", json)
        editor.apply()

    }

    companion object {
        var position: Int = 0
    }
}