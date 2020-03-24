package com.example.keepmynotes.viewmodel.repository

import androidx.lifecycle.LiveData
import com.example.keepmynotes.room.dao.KeepMyNoteDAO
import com.example.keepmynotes.room.entity.KeepMyNoteEntity

class KeepMyNoteRepository(private val noteDAO: KeepMyNoteDAO) {

    fun getNotes(): LiveData<List<KeepMyNoteEntity>>? {
        return noteDAO.getAllNotes()
    }

    suspend fun insert(entity: KeepMyNoteEntity) {
        noteDAO.addSingleNote(entity)
    }

    suspend fun update(entity: KeepMyNoteEntity) {
        noteDAO.updateNote(entity)
    }

    suspend fun delete(entity: KeepMyNoteEntity) {
        noteDAO.deleteNote(entity)
    }
}