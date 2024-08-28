package com.example.androidtesttask.screen.vm

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidtesttask.TestTaskApplication

/**
 * обьект для инициализации [ListProductViewModel]
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ListProductViewModel(testTaskApplication().container.ItemRepository)
        }
    }
}

fun CreationExtras.testTaskApplication(): TestTaskApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TestTaskApplication)