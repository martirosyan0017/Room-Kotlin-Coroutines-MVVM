package com.example.keepmynotes.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keepmynotes.interfaces.NoteItemClickListener
import com.example.keepmynotes.room.entity.KeepMyNoteEntity
import com.example.keepmynotes.view.adapter.viewholder.KeepMyNoteViewHolder
import com.example.keepmynotes.R

class KeepMyNoteAdapter(private val noteItemClickListener: NoteItemClickListener) : RecyclerView.Adapter<KeepMyNoteViewHolder>(){

    private lateinit var notes: List<KeepMyNoteEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepMyNoteViewHolder {
        return KeepMyNoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false),noteItemClickListener)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: KeepMyNoteViewHolder, position: Int) {
        notes[position].let {
            holder.bind(it)
        }
    }

     fun submitData(noteList : List<KeepMyNoteEntity>){
         this.notes = noteList
         notifyDataSetChanged()
    }
}