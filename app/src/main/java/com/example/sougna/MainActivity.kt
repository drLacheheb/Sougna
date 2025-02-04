package com.example.sougna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import com.example.sougna.ui.theme.TodoTaskScreen
import com.example.sougna.ui.theme.SougnaTheme.ProfileScreen
import com.example.sougna.ui.theme.SougnaTheme.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                //WelcomeScreen()
                //ProfileScreen()
            TodoTaskScreen();
        }
    }
}

/**
 * Main composable function for the UI layout
 * @param modifier Modifier for layout adjustments

@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    // TODO 1: Create state variables for text input and items list

    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue = "", // TODO 2: Connect to state
            onTextValueChange = { /* TODO 3: Update text state */ },
            onAddItem = { /* TODO 4: Add item to list */ },
            onSearch = { /* TODO 5: Implement search functionality */ }
        )

        // TODO 6: Display list of items using CardsList composable
        CardsList(emptyList())
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
            Button(onClick = { /* TODO 7: Handle add button click */ }) {
                Text("Add")
            }

            Button(onClick = { /* TODO 8: Handle search button click */ }) {
                Text("Search")
            }
        }
    }
}

*/