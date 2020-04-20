package com.example.habits.Notes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habits.R
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

        Log.i("FragmentNotes", "onCreateView called")

        // Action when the "Neue Notiz" Button is pressed
        view.createNoteClick.setOnClickListener {
            startActivity(Intent(activity, CreateNoteActivity::class.java))
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FragmentNotes", "onAttach called")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentNotes", "onCreate called")
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("FragmentNotes", "onActivityCreated called")
    }
    override fun onStart() {
        super.onStart()
        /*
        Hier sollte am besten das Laden der Notizen reinkommen, da es jedes mal aufgerufen wird sobald man hinzufügen drückt
         */
        Log.i("FragmentNotes", "onStart called")
    }
    override fun onResume() {
        super.onResume()
        Log.i("FragmentNotes", "onResume called")
    }
    override fun onPause() {
        super.onPause()
        Log.i("FragmentNotes", "onPause called")
    }
    override fun onStop() {
        super.onStop()
        Log.i("FragmentNotes", "onStop called")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentNotes", "onDestroyView called")
    }
    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentNotes", "onDetach called")
    }

    companion object {
        fun newInstance() = FragmentNotes()
    }
}


