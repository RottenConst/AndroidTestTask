package com.example.androidtesttask.screen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidtesttask.data.Item
import com.example.androidtesttask.data.ItemRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListProductViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    val items: LiveData<List<Item>> = liveData { emit(itemRepository.getAllItemsStream()) }

    fun parseUnixTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}