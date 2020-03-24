package com.example.keepmynotes.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.keepmynotes.room.entity.KeepMyNoteEntity

@Dao
interface KeepMyNoteDAO {

    @Insert
    suspend fun addSingleNote(addNote: KeepMyNoteEntity)

    @Insert
    suspend fun addMultipleNotes(vararg note: KeepMyNoteEntity)

   /* @Query("SELECT * FROM keepMyNote ORDER BY id DESC")
    fun getAllNotes() : List<KeepMyNoteEntity>*/

    @Query("SELECT * from keepMyNote ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<KeepMyNoteEntity>>

    @Update
    suspend fun updateNote(updateNote: KeepMyNoteEntity)

    @Delete
    suspend fun deleteNote(deleteNote: KeepMyNoteEntity)
}