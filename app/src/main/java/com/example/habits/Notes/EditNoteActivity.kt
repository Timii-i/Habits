package com.example.habits.Notes


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.Notes.FragmentNotes.Companion.noteList
import com.example.habits.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.edit_note.*
import kotlinx.android.synthetic.main.edit_note.NotizInhaltEingabe
import kotlinx.android.synthetic.main.edit_note.NotizTitel

class EditNoteActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_note)

        // Sets the individual text fields to the content of the note from that position
        setFields()

        // Action when "Ändern" button is pressed
        NotizÄndernClick.setOnClickListener {

            // Saves the input from the user in variables
            val noteName: String = NotizTitelEingabe.text.toString()
            val noteContent: String = NotizInhaltEingabe.text.toString()

            NotizTitel.error = null
            NotizTitelEingabe.error = null

            // Checks if the input fields are empty or not
            if (noteName.trim().isNotEmpty() && noteContent.trim().isNotEmpty() && noteName.trim().length <= 35) {
                //Toast.makeText(applicationContext, "NotizName: $noteName \nNotizInhalt: $noteContent", Toast.LENGTH_SHORT).show()

                FragmentNotes.noteList[position] =
                    Note(
                        noteName,
                        noteContent
                    )

                // Edits note in sharedPreferencs
                editSavedNote()

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

        // Action when the note name is longer than 35 characters
        NotizTitelEingabe.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(zielNameText: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (zielNameText.trim().length > 35) {
                    NotizTitelEingabe.error = "Zu langer Name"
                }
            }
        })
    }

    private fun setFields() {
        NotizTitelEingabe.setText(noteList[position].Name)
        NotizInhaltEingabe.setText(noteList[position].Content)
    }

    // Function to save the noteList into SharedPreferences
    private fun editSavedNote() {
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