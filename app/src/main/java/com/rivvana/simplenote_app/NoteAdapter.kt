package com.rivvana.simplenote_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.simplenote_app.room.Note

class NoteAdapter(
    private val notes: ArrayList<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.tvTitle.text = notes[position].title
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle= view.findViewById<TextView>(R.id.tv_title)
    }

    fun setData(list: List<Note>){
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }
}
