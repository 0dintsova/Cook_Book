package com.example.summerproject.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ingedients")
data class Ingredients(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "count")
    val count:Float,

    @ColumnInfo(name = "image", defaultValue = "0")
    val image:Int,

    @ColumnInfo(name = "ListId")
    val listId:Int,

)
