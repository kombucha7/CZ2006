package com.example.cz2006ver2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class Home1Recyclerr : RecyclerView.Adapter<Home1Recyclerr.ViewHolder>() {

    private var taskname = arrayOf("ahma 1", "ahma 2", "dispose of ahma", "donate ahma to the polyclinic", "ahma 5", "ahma 6", "ahma 7", "ahma 8")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.home1check, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkbox1.text = taskname[position]
    }

    override fun getItemCount(): Int {
        return taskname.size;
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var checkbox1: CheckBox

        init {
            checkbox1 = itemView.findViewById(R.id.home1check1)

            checkbox1.setOnClickListener{
                //TODO
                //connect to db to set task completed or not
            }
        }
    }
}