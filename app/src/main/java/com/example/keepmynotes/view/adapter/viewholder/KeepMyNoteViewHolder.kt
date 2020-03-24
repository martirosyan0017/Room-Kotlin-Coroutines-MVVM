package com.example.keepmynotes.view.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.keepmynotes.interfaces.NoteItemClickListener
import com.example.keepmynotes.room.entity.KeepMyNoteEntity
import kotlinx.android.synthetic.main.note_layout.view.*

open class KeepMyNoteViewHolder(itemView: View,  private val noteItemClickListener: NoteItemClickListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(notes: KeepMyNoteEntity){
        itemView.textViewTitle.text = notes.title
        itemView.text_view_note.text = notes.note

        itemView.root_card_view.setOnClickListener{
            noteItemClickListener.onItemClicked(it,notes)
        }
    }
}