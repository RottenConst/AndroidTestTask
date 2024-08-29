package com.example.androidtesttask.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item WHERE name LIKE :searchText")
    suspend fun searchItem(searchText: String): List<Item>
}