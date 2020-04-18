package com.example.habits.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habits.R
import com.example.habits.CreateNoteActivity
import kotlinx.android.synthetic.main.fragment_notes.view.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentNotes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        // Action when the "Neue Notiz" Button is pressed
        view.createNoteClick.setOnClickListener {
            startActivity(Intent(activity, CreateNoteActivity::class.java))
        }

        return view
    }

    companion object {
        fun newInstance() = FragmentNotes()
    }

    override fun onResume() {
        super.onResume()
    }
}


