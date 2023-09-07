package com.example.summerproject.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CookingSteps")
data class CookingSteps(

    @PrimaryKey(autoGenerate = true)
    val id :Int?,

    @ColumnInfo(name = "number step")
    val numberStep:String,

    @ColumnInfo(name = "instruction")
    val instruction:String,

    @ColumnInfo(name = "listId")
    val listId:Int
)
