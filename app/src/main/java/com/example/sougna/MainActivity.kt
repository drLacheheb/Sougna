package com.example.sougna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                   // Greeting(
                    //    name = "Heyam",
                     //   modifier = Modifier.padding(innerPadding)
                   // )
                    FirstUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



/**
 * Main composable function for the UI layout
 * @param modifier Modifier for layout adjustments
 */

@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    //those are the State variables
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue = textValue, // here i'm connecting the textValue
            onTextValueChange = { newValue -> textValue = newValue }, // to update it like i did on react
            onAddItem = {
                // Add non-empty items to the list
                if (textValue.isNotBlank()) {
                    allItems.add(textValue)
                    textValue = "" // Clear input field
                }
            },
            onSearch = { query ->
                searchQuery = query // updating the search
            }
        )

        // this is for filtering
        val displayedItems = if (searchQuery.isEmpty()) {
            allItems
        } else {
            allItems.filter { it.contains(searchQuery, ignoreCase = true) }
        }

        // displaying the filtered list
        CardsList(displayedItems)
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
    onAddItem: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Column {
        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter text...") }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //to handel the buttons and connecting them
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
 * Composable for displaying a list of items in cards
 * @param displayedItems List of items to display
 */
@Composable
fun CardsList(displayedItems: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(displayedItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(text = item, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
