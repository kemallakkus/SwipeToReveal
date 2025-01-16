package com.example.swipetoreveal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<SwipeUi>>(emptyList())
    val items = _items.asStateFlow()

    init {
        // Initial list
        _items.value = (1..100).map {
            SwipeUi(
                id = it,
                name = "Item $it",
                isShow = false,
            )
        }
    }

    fun removeItem(item: SwipeUi) {
        _items.update { currentList ->
            currentList.filter { it.id != item.id }
        }
    }

    fun addItem(item: SwipeUi) {
        _items.update { currentList ->
            currentList + item.copy(
                id = currentList.maxOf { it.id } + 1,
                name = "Item ${currentList.maxOf { it.id } + 1}",
            )
        }
    }

    fun updateItem(item: SwipeUi) {
        _items.update { currentList ->
            currentList.map { if (it.id == item.id) item else it }
        }
    }
}