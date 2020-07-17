package com.example.habits.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.R

class TipAdapter(private val tips: ArrayList<String>) : RecyclerView.Adapter<TipAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.tip_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = tips.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tipContent.text = tips[position]
    }

    // Sets the text to the corresponding tip
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tipContent: TextView = itemView.findViewById(R.id.tipContent)
    }
}