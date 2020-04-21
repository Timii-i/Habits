package com.example.habits.Notes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.R
import com.example.habits.ui.adapters.NoteAdapter
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.createNoteClick

/**
 * A simple [Fragment] subclass.
 */
class FragmentNotes : Fragment() {

    private var isUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        addNotes()

        Log.i("FragmentNotes", "onCreateView called")

        // Action when the "Neue Notiz" Button is pressed
        view.createNoteClick.setOnClickListener {
            startActivity(Intent(activity, CreateNoteActivity::class.java))
        }

        return view
    }

    // onStart
    override fun onStart() {
        super.onStart()


        rv_note_list.layoutManager = LinearLayoutManager(activity)
        rv_note_list.adapter = NoteAdapter(
            noteList,
            activity
        )
    }

    companion object {

        // The List where the Notes are saved to show in the "Notizen" Tab
        val noteList: ArrayList<Note> = ArrayList()

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


