package com.fractaldev.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fractaldev.composeexample.lambdatest.LambdasTestScreen
import com.fractaldev.composeexample.lazylisttest.LazyListTestScreen
import com.fractaldev.composeexample.statehoisting.StateHoistingTestScreen
import com.fractaldev.composeexample.ui.theme.ComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
//                LambdasTestScreen()
//                LazyListTestScreen()
                StateHoistingTestScreen()
            }
        }
    }
}