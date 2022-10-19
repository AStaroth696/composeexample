package com.fractaldev.composeexample.lambdatest

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fractaldev.composeexample.utils.recomposeHighlighter
import org.koin.androidx.compose.koinViewModel

@Composable
fun LambdasTestScreen(viewModel: LambdaTestViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxSize().recomposeHighlighter()) {
//        Input(input = state.input, onInputChange = { viewModel.changeInput(it) })
        Input(input = state.input, onInputChange = viewModel::changeInput)
        Button(onClick = {
            viewModel.changeLoadingStatus()
        }) {
            Text(text = "Change flag", modifier = Modifier.recomposeHighlighter())
        }
    }
}

@Composable
fun Input(input: String, onInputChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .recomposeHighlighter()
    ) {
        TextField(
            value = input,
            onValueChange = onInputChange,
            modifier = Modifier
                .fillMaxWidth()
                .recomposeHighlighter()
        )
    }
}