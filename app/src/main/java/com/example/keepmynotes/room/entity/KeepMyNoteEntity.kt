package com.example.keepmynotes.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "keepMyNote")
class KeepMyNoteEntity (
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "note")
    val note: String?
) : Parcelable {

    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}