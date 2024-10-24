package com.example.lab8

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StringList {
    private external fun addString(string: String)
    private external fun removeLastString()
    private external fun getStringList(): Array<String>
    private external fun getAllStringsAsCommaSeparated(): String

    @Composable
    fun StringListApp(modifier: Modifier = Modifier) {
        var inputText by remember { mutableStateOf("") }
        var strings by remember { mutableStateOf(getStringList().toList()) }
        var commaSeparatedStrings by remember { mutableStateOf(getAllStringsAsCommaSeparated()) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Enter a word") })
            CustomButton(
                text = "Add Word",
                onClick = {
                    if (inputText.isNotEmpty())
                        addString(inputText)
                    inputText = ""
                    strings = getStringList().toList()
                    commaSeparatedStrings = getAllStringsAsCommaSeparated()
                }
            )
            CustomButton(
                text = "Remove Last Word",
                onClick = {
                    removeLastString()
                    strings = getStringList().toList()
                    commaSeparatedStrings = getAllStringsAsCommaSeparated()
                }
            )
            Text("Strings List: ${strings.joinToString(", ")}",
                fontSize = 24.sp)
            Text("Strings: $commaSeparatedStrings",
                fontSize = 24.sp)
        }
    }
}

@Composable
private fun CustomButton(
    text: String,
    onClick: () -> Unit
){
    Button(onClick = onClick){
        Text(text = text, fontSize = 20.sp)
    }
}