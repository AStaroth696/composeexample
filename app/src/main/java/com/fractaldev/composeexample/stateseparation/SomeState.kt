package com.fractaldev.composeexample.stateseparation

data class SomeState(
    val isLoading: Boolean,
    val items: List<String>,
    val enabled: Boolean,
    val error: String?
) {

    companion object {

        fun initial(): SomeState {
            return SomeState(
                isLoading = false,
                items = emptyList(),
                enabled = false,
                error = null
            )
        }
    }
}
