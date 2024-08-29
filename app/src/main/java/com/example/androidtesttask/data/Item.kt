package com.example.androidtesttask.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidtesttask.utils.TagsConvertor

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val time: Long,
    @TypeConverters(TagsConvertor::class)
    val tags: List<String>,
    var amount: Int
)
