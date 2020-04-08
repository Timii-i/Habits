package com.example.habits.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.habits.R
import java.net.URLClassLoader.newInstance

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
        // To load in the different Fragments when the tabs are pressed
        return when (position) {
            0 -> FragmentGoals()
            1 -> FragmentNotes()
            else -> FragmentTips()
        }
        //return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}