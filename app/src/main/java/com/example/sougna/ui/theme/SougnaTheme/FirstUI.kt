package com.example.sougna.ui.theme.SougnaTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FirstUI(modifier: Modifier = Modifier) {
// State variables for text input, search query, and items list
    var textInput by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var itemList by remember { mutableStateOf(listOf("Task 1", "Task 2", "Task 3")) }

    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
// Search & Input Bar
        SearchInputBar(
            textValue = textInput,
            onTextValueChange = { textInput = it },
            onAddItem = { newItem ->
                if (newItem.isNotBlank() && newItem !in itemList) {
                    itemList = itemList + newItem.trim()
                    textInput = "" // Clear input
                }
            },
            onSearch = { query -> searchQuery = query }
        )

        Spacer(modifier = Modifier.height(16.dp))

// Filtered list based on search
        val filteredList = if (searchQuery.isBlank()) itemList
        else itemList.filter { it.contains(searchQuery, ignoreCase = true) }

// Display list using CardsList
        CardsList(filteredList)
    }
}

/**
 * Composable for search and input controls
 */
@Composable
fun SearchInputBar(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    onAddItem: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Column {
// Text Input Field
        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter text...") }
        )

        Spacer(modifier = Modifier.height(8.dp))

// Add & Search Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onAddItem(textValue) }) {
                Text("Add")
            }

            Button(onClick = { onSearch(textValue) }) {
                Text("Search")
            }
        }
    }
}

/**
 * Composable to display a list of items using Cards
 */
@Composable
fun CardsList(items: List<String>) {
    if (items.isEmpty()) {
        Text(
            text = "No items found!",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.error
        )
    } else {
        LazyColumn {
            items(items) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = item, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstUI() {
    FirstUI()
}
