package com.fractaldev.composeexample.lazylisttest

import androidx.lifecycle.ViewModel
import com.fractaldev.composeexample.utils.toStableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

class LazyListTestViewModel : ViewModel() {

    private val _state = MutableStateFlow(LazyListTestState.default())
    val state = _state.asStateFlow()

    fun createItems() {
        _state.update {
            it.copy(items = generateItems())
        }
    }

    private fun generateItems(): List<TestItem> {
        return (0 until 20).map {
            TestItem(
                id = UUID.randomUUID().toString(),
                name = "Test Item $it",
                clickCounter = 0
            )
        }
    }

    fun onItemClick(item: TestItem) {
        _state.update {
            it.copy(
                items = it.items.map {
                    if (it.id == item.id) {
                        it.copy(clickCounter = it.clickCounter + 1)
                    } else {
                        it
                    }
                }.toStableList()
            )
        }
    }

    fun changeLoadingStatus() {
        _state.update {
            it.copy(isLoading = !it.isLoading)
        }
    }
}
