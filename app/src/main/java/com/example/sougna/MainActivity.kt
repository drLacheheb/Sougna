package com.example.sougna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sougna.ui.theme.SougnaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SougnaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Main composable function for the UI layout
 * @param modifier Modifier for layout adjustments
 */
@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    // State variables to manage the text input and item list
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }

    // Filter logic for displaying items
    val displayedItems = if (searchQuery.isEmpty()) {
        allItems
    } else {
        allItems.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        // Search input bar with text field and buttons
        SearchInputBar(
            textValue = textValue,
            onTextValueChange = { textValue = it },
            onAddItem = {
                // Add item logic
                if (textValue.isNotBlank()) {
                    allItems.add(textValue)
                    textValue = "" // Clear the input after adding
                }
            },
            onSearch = { query -> searchQuery = query }
        )

        // Display list of items using LazyColumn
        CardsList(displayedItems = displayedItems) {
            allItems.remove(it) // Handle item deletion
        }
    }
}

/**
 * Composable for search and input controls
 * @param textValue Current value of the input field
 * @param onTextValueChange Callback for text changes
 * @param onAddItem Callback for adding new items
 * @param onSearch Callback for performing search
 */
@Composable
fun SearchInputBar(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    onAddItem: () -> Unit,
    onSearch: (String) -> Unit
) {
    Column {
        // TextField for input
        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("input text...") }
        )

        // Buttons for Add and Search
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onAddItem) {
                Text("Add")
            }

            Button(onClick = { onSearch(textValue) }) {
                Text("Search")
            }
        }
    }
}

/**
 * Composable for displaying a list of items in cards
 * @param displayedItems List of items to display
 * @param onDelete Callback to delete an item
 */
@Composable
fun CardsList(displayedItems: List<String>, onDelete: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(displayedItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = item)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { onDelete(item) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SougnaTheme {
        FirstUI(modifier = Modifier.fillMaxSize())
    }
}
