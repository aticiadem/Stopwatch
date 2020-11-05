package com.adematici.stopwatch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class TimeAdapter(private var timeArrayList: ArrayList<String>) : RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {

    class TimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun getItemCount(): Int {
        return timeArrayList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return TimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.itemView.textView.text = timeArrayList[position]
    }
}