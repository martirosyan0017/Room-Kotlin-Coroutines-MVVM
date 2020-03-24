package com.example.keepmynotes.interfaces

import android.view.View
import com.example.keepmynotes.room.entity.KeepMyNoteEntity

interface NoteItemClickListener {
    fun onItemClicked(view: View, notes: KeepMyNoteEntity)
}