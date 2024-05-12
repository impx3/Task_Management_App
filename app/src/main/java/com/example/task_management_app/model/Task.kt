package com.example.task_management_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Parcelize
@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val taskName: String,
    val taskDesc: String,
    val priority: Int,
    //val deadline: Date,
):Parcelable