package com.fractaldev.composeexample.lambdatest

data class LambdaTestState(
    val isLoading: Boolean,
    val input: String
) {

    companion object {

        fun initial(): LambdaTestState {
            return LambdaTestState(
                isLoading = false,
                input = ""
            )
        }
    }
}
