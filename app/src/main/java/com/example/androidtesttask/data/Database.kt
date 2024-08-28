package com.example.androidtesttask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidtesttask.utils.TagsConvertor

@Database(entities = [Item::class], version = 1, exportSchema = false)
@TypeConverters(TagsConvertor::class)
abstract class TestTaskDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: TestTaskDatabase? = null

        fun getDatabase(context: Context): TestTaskDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TestTaskDatabase::class.java,
                    "database"
                )
                    .createFromAsset("database/data.db")
                    .build().also { Instance = it}
            }
        }
    }

}