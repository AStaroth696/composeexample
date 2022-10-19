package com.fractaldev.composeexample.lazylisttest

import com.fractaldev.composeexample.utils.StableList

data class LazyListTestState(
    val isLoading: Boolean,
    val items: List<TestItem>
) {

    companion object {

        fun default(): LazyListTestState {
            return LazyListTestState(
                isLoading = false,
                items = emptyList()
            )
        }

    }
}
