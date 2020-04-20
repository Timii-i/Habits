package com.example.habits.Notes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.R
import kotlinx.android.synthetic.main.create_note.*

class CreateNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_note)

        // Action when "Hinzufügen" button is pressed
        NotizHinzufügenClick.setOnClickListener {
            finish()
        }
        }
}
