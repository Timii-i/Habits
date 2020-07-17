package com.example.habits.Notes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.Notes.FragmentNotes.Companion.noteList
import com.example.habits.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.create_note.*

class CreateNoteActivity : AppCompatActivity() {
    private var color: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_note)

        // Actions when a color is pressed
        NotizFarbeStandard.setOnClickListener {
            color = getString(R.string.goal_color_standard)

            uncheckColors(NotizFarbeBlau, NotizFarbeRot, NotizFarbeOrange, NotizFarbeGrau)
            checkColor(NotizFarbeStandard)
        }
        NotizFarbeBlau.setOnClickListener {
            color = getString(R.string.goal_color_blue)

            uncheckColors(NotizFarbeStandard, NotizFarbeRot, NotizFarbeOrange, NotizFarbeGrau)
            checkColor(NotizFarbeBlau)
        }
        NotizFarbeRot.setOnClickListener {
            color = getString(R.string.goal_color_red)

            uncheckColors(NotizFarbeBlau, NotizFarbeStandard, NotizFarbeOrange, NotizFarbeGrau)
            checkColor(NotizFarbeRot)
        }
        NotizFarbeOrange.setOnClickListener {
            color = getString(R.string.goal_color_orange)

            uncheckColors(NotizFarbeBlau, NotizFarbeRot, NotizFarbeStandard, NotizFarbeGrau)
            checkColor(NotizFarbeOrange)
        }
        NotizFarbeGrau.setOnClickListener {
            color = getString(R.string.goal_color_gray)

            uncheckColors(NotizFarbeBlau, NotizFarbeRot, NotizFarbeOrange, NotizFarbeStandard)
            checkColor(NotizFarbeGrau)
        }

        // Action when "Hinzufügen" button is pressed
        NotizHinzufügenClick.setOnClickListener {

            // Saves the input from the user in variables
            val noteName: String = NotizNameEingabe.text.toString()
            val noteContent: String = NotizInhaltEingabe.text.toString()
            var noteColor: String = ""
            if (color != "") {
                noteColor = color
            }

            NotizNameEingabe.error = null
            NotizInhaltEingabe.error = null


            // Checks if the input fields are empty or not
            if (noteName.trim().isNotEmpty() && noteContent.trim().isNotEmpty() && noteName.trim().length <= 35) {

                // Adds the user input from create_note into noteList to display it in the "Notizen" Tab
                noteList.add(Note(noteName, noteContent, noteColor))

                // Saves the noteList into sharedPreferences
                saveNotes()

                finish()
            } else {
                if (noteName.trim().isEmpty()) {
                    NotizNameEingabe.error = getString(R.string.missing_input)
                }
                if (noteContent.trim().isEmpty()) {
                    NotizInhaltEingabe.error = getString(R.string.missing_input)
                }
            }
        }

        // Action when the note name is longer than 35 characters
        NotizNameEingabe.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(zielNameText: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (zielNameText.trim().length > 35) {
                    NotizNameEingabe.error = getString(R.string.lengthy_name)
                }
            }
        })
    }

    // Function to save the noteList into SharedPreferences
    private fun saveNotes() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(getString(R.string.note_preferences_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(noteList)
        editor.putString(getString(R.string.notes_key), json)
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
