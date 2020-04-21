package com.example.habits.Notes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.R
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

                FragmentNotes.noteList.add(Note(noteName, noteContent))

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
}
