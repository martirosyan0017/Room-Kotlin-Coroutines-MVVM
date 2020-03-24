package com.example.keepmynotes.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.keepmynotes.room.dao.KeepMyNoteDAO
import com.example.keepmynotes.room.entity.KeepMyNoteEntity

@Database(entities = [KeepMyNoteEntity::class], version = 1)
abstract class KeepMyNoteDataBase : RoomDatabase() {

    abstract fun getNoteDao() : KeepMyNoteDAO

    companion object {
        // Singleton prevents multiple instances of database
        @Volatile
        private var INSTANCE: KeepMyNoteDataBase? = null

        fun getDatabase(context: Context): KeepMyNoteDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KeepMyNoteDataBase::class.java,
                    "note_name.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}