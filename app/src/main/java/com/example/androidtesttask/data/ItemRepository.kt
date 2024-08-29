package com.example.androidtesttask.data

class ItemRepository(private val itemDao: ItemDao) {
    suspend fun deleteItem(item: Item) = itemDao.delete(item)

    suspend fun updateItem(item: Item) = itemDao.update(item)

    suspend fun searchItem(search: String) = itemDao.searchItem(search)
}