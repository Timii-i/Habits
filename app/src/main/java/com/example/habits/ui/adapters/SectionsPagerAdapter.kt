package com.example.habits.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.habits.Goals.FragmentGoals
import com.example.habits.Notes.FragmentNotes
import com.example.habits.Motivation.FragmentMotivation
import com.example.habits.R

private val TAB_TITLES = arrayOf(
        R.string.tab_text_goals,
        R.string.tab_text_notes,
        R.string.tab_text_tips
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1)
        return when(position) {
            0 -> FragmentGoals()
            1 -> FragmentNotes()
            2 -> FragmentMotivation()
            else -> FragmentGoals()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}