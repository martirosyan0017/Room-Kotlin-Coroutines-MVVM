package com.example.keepmynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.keepmynotes.room.database.KeepMyNoteDataBase
import com.example.keepmynotes.room.entity.KeepMyNoteEntity
import com.example.keepmynotes.viewmodel.repository.KeepMyNoteRepository
import kotlinx.coroutines.launch

class KeepMyNoteViewModel(application: Application) : AndroidViewModel(application) {

    private var roomRepository: KeepMyNoteRepository? = null
    var notesLiveDataList: LiveData<List<KeepMyNoteEntity>>? = null

    init {
        val noteDao = KeepMyNoteDataBase.getDatabase(application).getNoteDao()
        roomRepository = KeepMyNoteRepository(noteDao)
    }

    fun saveSingleNote(note: KeepMyNoteEntity) = viewModelScope.launch {
        roomRepository?.insert(note)
    }

    fun updateSingleNote(note: KeepMyNoteEntity) = viewModelScope.launch {
       roomRepository?.update(note)
    }

    fun deleteSingleNote(note: KeepMyNoteEntity) = viewModelScope.launch {
       roomRepository?.delete(note)
    }

    fun getAllNotes(){
        notesLiveDataList = roomRepository?.getNotes()
    }
}