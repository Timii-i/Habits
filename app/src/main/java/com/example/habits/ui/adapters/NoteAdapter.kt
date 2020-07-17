package com.example.habits.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.*
import com.example.habits.Notes.EditNoteActivity
import com.example.habits.Notes.FragmentNotes.Companion.noteList
import com.example.habits.Notes.Note
import com.google.gson.Gson
import kotlinx.android.synthetic.main.note_list_item.view.*

class NoteAdapter (private val items: ArrayList<Note>, private val context: Context?): RecyclerView.Adapter<NoteViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_list_item, parent,false))
    }

    // Binds each goal in the ArrayList to a view
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // Sets the Name, Duration and Reminder of each element in the RecyclerView
        holder.tvNoteName.text = items[position].Name
        holder.tvNoteContent.text = items[position].Content
        setDeleteButton(holder, position)
        setEditButton(holder, position)
        setBackground(holder, position)
    }

    // Return the number of notes in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Function to set the background for each goal
    private fun setBackground(holder: NoteViewHolder, position: Int) {
        if (items[position].Color == "" || items[position].Color == context!!.getString(R.string.notes_color_standard)) {
            holder.layout.setBackgroundResource(R.drawable.button_standard)

        } else if (items[position].Color == context.getString(R.string.notes_color_blue)) {
            holder.layout.setBackgroundResource(R.drawable.button_blue)

        } else if (items[position].Color == context.getString(R.string.notes_color_red)) {
            holder.layout.setBackgroundResource(R.drawable.button_red)

        } else if (items[position].Color == context.getString(R.string.notes_color_orange)) {
            holder.layout.setBackgroundResource(R.drawable.button_orange)

        } else if (items[position].Color == context.getString(R.string.notes_color_gray)) {
            holder.layout.setBackgroundResource(R.drawable.button_gray)

        }
    }

    // Function to set up the delete Button
    private fun setDeleteButton(holder: NoteViewHolder, position: Int) {
        holder.btnDelete.setOnClickListener{
            // Opens an AlertBox when the delete button is pressed
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle(R.string.dialog_delete_note_Name)  // "name" previously was goal_title
            alertDialog.setMessage(R.string.dialog_delete_note_confirmation)

            // If the user presses "OK" the goal gets deleted
            alertDialog.setPositiveButton(context!!.getString(R.string.dialog_alert_positive), object: DialogInterface.OnClickListener {
                @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    noteList.removeAt(position)
                    deleteNote()
                    notifyDataSetChanged()
                }
            })

            // If the user presses "Abbrechen" the AlertBox closes and nothing happens
            alertDialog.setNegativeButton(context.getString(R.string.dialog_alert_negative), null)

            alertDialog.show()
        }
    }

    // Function to set up the edit button and open EditNoteActivity when edit button is pressed
    private fun setEditButton(holder: NoteViewHolder, position: Int) {
        holder.btnUpdate.setOnClickListener {
            EditNoteActivity.position = position
            context?.startActivity(Intent(context, EditNoteActivity::class.java))
        }
    }

    // Function to delete the element from noteList and save it into SharedPreferences
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun deleteNote() {
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(R.string.note_preferences_name.toString(), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(noteList)
        editor.putString(R.string.notes_key.toString(), json)
        editor.apply()

    }
}

class NoteViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each goal
    val tvNoteName: TextView = view.textNoteName
    val tvNoteContent: TextView = view.textNoteContent
    val btnDelete: ImageButton = view.findViewById(R.id.buttonDelete)
    val btnUpdate: ImageButton = view.findViewById(R.id.buttonUpdate)
    val layout: RelativeLayout = view.findViewById(R.id.noteLayout)
}