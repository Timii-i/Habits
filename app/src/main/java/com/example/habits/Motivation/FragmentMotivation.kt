package com.example.habits.Motivation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habits.R
import kotlinx.android.synthetic.main.fragment_motivation.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentMotivation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_motivation, container, false)
    }

    companion object {
        fun newInstance() = FragmentMotivation()
    }
}
