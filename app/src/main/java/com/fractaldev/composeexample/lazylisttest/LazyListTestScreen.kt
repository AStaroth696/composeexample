package com.fractaldev.composeexample.lazylisttest

import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import com.fractaldev.composeexample.utils.StableList
import com.fractaldev.composeexample.utils.recomposeHighlighter
import com.fractaldev.composeexample.utils.toStableList
import org.koin.androidx.compose.koinViewModel

@Composable
fun LazyListTestScreen(viewModel: LazyListTestViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = viewModel::changeLoadingStatus) {
                Text(text = "Loading: ${state.isLoading}")
            }
            Button(onClick = viewModel::createItems) {
                Text(text = "Create items")
            }
        }
//        TestItems(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(), items = state.items.toStableList(),
//            onItemClick = viewModel::onItemClick
//        )
        TestItems(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), items = state.items,
            onItemClick = viewModel::onItemClick
        )
    }
}

@Composable
fun TestItems(modifier: Modifier = Modifier, items: List<TestItem>, onItemClick: (TestItem) -> Unit) {
    LazyColumn(modifier = modifier.recomposeHighlighter(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(items, key = { it.id }) {
                TestItemView(item = it, onItemClick = onItemClick)
            }
        })
}

@Composable
fun TestItemView(item: TestItem, onItemClick: (TestItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, color = Color.White, shape = MaterialTheme.shapes.medium)
            .clickable(onClick = { onItemClick(item) })
            .padding(16.dp)
            .recomposeHighlighter()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = item.name)
            Text(text = "Was clicked ${item.clickCounter} times")
            Text(text = item.id)
        }
    }
}

//@Composable
//fun TestItems(modifier: Modifier, items: StableList<TestItem>, onItemClick: (TestItem) -> Unit) {
//    LazyColumn(modifier = modifier.recomposeHighlighter(),
//        contentPadding = PaddingValues(16.dp),
//        verticalArrangement = Arrangement.spacedBy(4.dp),
//        content = {
//            items(items, key = { it.id }) {
//                TestItemView(item = it, onItemClick = onItemClick)
//            }
//        })
//}