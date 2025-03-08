package com.example.sogna

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun FirstUI() {
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }

    val displayedItems = if (searchQuery.isEmpty()) {
        allItems
    } else {
        allItems.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = { Text("entre") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    if (textValue.isNotBlank()) {
                        allItems.add(textValue)
                        textValue = ""
                    }
                }
            ) {
                Text("addition")
            }

            Button(
                onClick = {
                    searchQuery = textValue
                }
            ) {
                Text("research")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (displayedItems.isEmpty() && searchQuery.isNotEmpty()) {
            Text(
                text = "error",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            CardsList(displayedItems) { itemToDelete ->
                allItems.remove(itemToDelete)
            }
        }
    }
}
