package com.example.lab8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Counter {
    private external fun incrementCounter(counterPtr: Long)
    private external fun resetCounter(counterPtr: Long)
    private external fun getCounterValue(counterPtr: Long): Int

    @Composable
    fun CounterApp(
        counterPtr: Long,
        counterValue: Long,
        modifier: Modifier = Modifier
    ){
        Column (
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterVertically)
        ) {
            Counter(
                onIncrement = { incrementCounter(counterPtr) },
                onReset = { resetCounter(counterPtr) },
                getValue = { getCounterValue(counterPtr) },
            )
            Counter(
                onIncrement = { incrementCounter(counterValue) },
                onReset = { resetCounter(counterValue) },
                getValue = { getCounterValue(counterValue) },
            )
        }
    }
}

@Composable
private fun Counter(
    onIncrement: () -> Unit,
    onReset: () -> Unit,
    getValue: () -> Int,
){
    var counterValue by remember { mutableIntStateOf(getValue()) }

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${counterValue}", fontSize = 80.sp, fontWeight = FontWeight.W600)
            CounterButton(text = "Increment", onClick = {
                onIncrement()
                counterValue = getValue()
            })
            CounterButton(text = "Reset", onClick = {
                onReset()
                counterValue = getValue()
            })
        }
    }
}

@Composable
private fun CounterButton(
    text: String,
    onClick: () -> Unit,
){
    Button(
        modifier = Modifier.fillMaxWidth(0.5f),
        onClick = { onClick() },
    ) {
        Text(text = text, fontSize = 30.sp)
    }
}