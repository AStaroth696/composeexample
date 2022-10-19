package com.fractaldev.composeexample.stateseparation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private const val CHARS = "qwertyuiopasdfghjklzxcvbnm"

//@Composable
//fun StateSeparationScreen() {
//    var state by remember {
//        mutableStateOf(SomeState.initial())
//    }
//    Column(modifier = Modifier.fillMaxSize()) {
//        SomeItems(state = state, onItemClick = { item ->
//            state = state.copy(
//                enabled = true,
//                items = state.items.filter { it != item }
//            )
//        })
//        AddItemButton(state = state) {
//            val items = state.items + buildString {
//                repeat(10) {
//                    append(CHARS.random())
//                }
//            }
//            state = state.copy(
//                enabled = items.size < 20,
//                items = items
//            )
//        }
//        Error(state = state)
//    }
//    Loader(state)
//}
//
//@Composable
//fun SomeItems(modifier: Modifier = Modifier, state: SomeState, onItemClick: (String) -> Unit) {
//    LazyColumn(
//        modifier = modifier,
//        contentPadding = PaddingValues(16.dp),
//        verticalArrangement = Arrangement.spacedBy(4.dp),
//        content = {
//            items(count = state.items.size, key = { it }) {
//                SomeItem(state = state, index = it, onClick = onItemClick)
//            }
//        })
//}
//
//@Composable
//fun SomeItem(state: SomeState, index: Int, onClick: (String) -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .border(2.dp, color = Color.White)
//            .clickable(onClick = { onClick(state.items[index]) })
//            .padding(16.dp)
//    ) {
//        Text(text = state.items[index])
//    }
//}
//
//@Composable
//fun AddItemButton(state: SomeState, onClick: () -> Unit) {
//    Button(onClick = onClick, enabled = state.enabled) {
//        Text(text = "Add item")
//    }
//}
//
//@Composable
//fun Error(state: SomeState) {
//    state.error?.also { error ->
//        Text(text = error, modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.error)
//    }
//}
//
//@Composable
//fun Loader(state: SomeState) {
//    if (state.isLoading) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
//        }
//    }
//}





















@Composable
fun StateSeparationScreen() {

    var state by remember {
        mutableStateOf(SomeState.initial())
    }
    Column(modifier = Modifier.fillMaxSize()) {
        SomeItems(items = state.items, onItemClick = { item ->
            state = state.copy(
                enabled = true,
                items = state.items.filter { it != item }
            )
        })
        AddItemButton(enabled = state.enabled) {
            val items = state.items + buildString {
                repeat(10) {
                    append(CHARS.random())
                }
            }
            state = state.copy(
                enabled = items.size < 20,
                items = items
            )
        }
        state.error?.also { error ->
            Error(error)
        }
    }
    if (state.isLoading) {
        Loader()
    }
}

@Composable
fun SomeItems(modifier: Modifier = Modifier, items: List<String>, onItemClick: (String) -> Unit) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(items, key = { it }) {
                SomeItem(item = it, onClick = onItemClick)
            }
        })
}

@Composable
fun SomeItem(item: String, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, color = Color.White)
            .clickable(onClick = { onClick(item) })
            .padding(16.dp)
    ) {
        Text(text = item)
    }
}

@Composable
fun AddItemButton(enabled: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick, enabled = enabled) {
        Text(text = "Add item")
    }
}

@Composable
fun Error(message: String) {
    Text(text = message, modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.error)
}

@Composable
fun Loader() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}