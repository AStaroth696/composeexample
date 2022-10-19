package com.fractaldev.composeexample.statehoisting

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StateHoistingTestScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        StatefulInput()
        StatefulAnimation()
    }
}

@Composable
fun StatefulInput() {
    var value by remember {
        mutableStateOf("")
    }
    StatelessInput(
        value = value,
        onValueChange = { value = it })
}

@Composable
fun StatelessInput(value: String, onValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue)
            .padding(16.dp)
    ) {
        TextField(value = value, onValueChange = onValueChange)
    }
}

enum class TransitionState {
    FORWARD, BACKWARD
}

@Composable
fun StatelessAnimation(color: Color, height: Dp, onAnimateClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onAnimateClick) {
            Text(text = "Animate")
        }
    }
}

@Composable
fun StatefulAnimation() {
    var transitionState by remember {
        mutableStateOf(TransitionState.FORWARD)
    }
    val transition = updateTransition(targetState = transitionState, label = "transition")
    val color by transition.animateColor(
        transitionSpec = {
            tween(durationMillis = 1000)
        },
        label = "color_transition"
    ) {
        when (it) {
            TransitionState.FORWARD -> Color.Red
            TransitionState.BACKWARD -> Color.Yellow
        }
    }
    val height by transition.animateDp(
        transitionSpec = {
            tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        },
        label = "height_transition"
    ) {
        when (it) {
            TransitionState.FORWARD -> 200.dp
            TransitionState.BACKWARD -> 100.dp
        }
    }
    StatelessAnimation(color = color, height = height) {
        transitionState = when (transitionState) {
            TransitionState.FORWARD -> TransitionState.BACKWARD
            TransitionState.BACKWARD -> TransitionState.FORWARD
        }
    }
}