package com.example.androidtesttask.utils

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TagsConvertor {

    @TypeConverter
    fun toTags(tags: String): List<String> {
        return Json.decodeFromString(tags)
    }

    @TypeConverter
    fun fromTags(tags: List<String>): String {
        return Json.encodeToString(tags)
    }
}