package com.example.androidtesttask.data

import android.content.Context

/**
 * [AppContainer] для внедрения зависимостей
 */
interface AppContainer {
    val ItemRepository: ItemRepository
}

/**
 * реализация [AppContainer] для внедрения [TestTaskDatabase]
 */
class AppDataContainer(private val context: Context): AppContainer {
    override val ItemRepository: ItemRepository by lazy {
            ItemRepository(TestTaskDatabase.getDatabase(context).itemDao())
    }
}