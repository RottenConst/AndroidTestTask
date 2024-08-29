package com.example.androidtesttask.screen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtesttask.data.Item
import com.example.androidtesttask.data.ItemRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListProductViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    private val _products: MutableLiveData<List<Item>> = MutableLiveData()
    val products: LiveData<List<Item>> get() = _products

    /**
     * Метод поиска поваров в базе данных
     */
    fun search(query: String): String {
        viewModelScope.launch {
            _products.value = itemRepository.searchItem("%$query%")
        }
        return query
    }

    /**
     * Метод обновление количества поваров
     */
    fun updateItem(item: Item) {
        viewModelScope.launch {
            _products.value?.find { item.id == it.id}?.amount = item.amount
            itemRepository.updateItem(item)
        }
    }

    /**
     * Метод удаления поваров из базы данных
     * можно было по лучше но для быстроты пока так
     */
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            val items = _products.value?.toMutableList()
            items?.remove(item)
            _products.value = items ?: emptyList()
            itemRepository.deleteItem(item)
        }
    }

    /**
     * Метов коветирования даты в формат "dd.MM.yyyy"
     */
    fun parseUnixTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}