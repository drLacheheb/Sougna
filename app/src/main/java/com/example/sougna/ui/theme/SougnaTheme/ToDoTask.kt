package com.example.sougna.ui.theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TodoTaskScreen() {
    var textInput by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var itemList by remember { mutableStateOf(listOf("Buy Groceries", "Call Mom", "Workout", "Read Book")) }
    var recentlyDeletedItem by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input field for adding new items
        TextField(
            value = textInput,
            onValueChange = { textInput = it },
            label = { Text("Enter Task") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display error message if empty input
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
        }

        // Button to add item to list
        Button(
            onClick = {
                if (textInput.isBlank()) {
                    errorMessage = "Task cannot be empty!"
                } else if (itemList.contains(textInput.trim())) {
                    errorMessage = "Task already exists!"
                } else {
                    itemList = itemList + textInput.trim()
                    textInput = "" // Clear input field
                    errorMessage = null // Reset error
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search field
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Task") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Filtered list based on search
        val filteredList = itemList.filter { it.contains(searchQuery, ignoreCase = true) }

        if (filteredList.isEmpty() && searchQuery.isNotBlank()) {
            Text(
                text = "No tasks found!",
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        } else {
            LazyColumn {
                items(filteredList) { item ->
                    TaskCard(
                        task = item,
                        onDelete = {
                            // Remove item and store for undo
                            recentlyDeletedItem = item
                            itemList = itemList - item

                            // Show snackbar with Undo option
                            coroutineScope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Task deleted",
                                    actionLabel = "Undo",
                                    duration = SnackbarDuration.Short
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    itemList = itemList + (recentlyDeletedItem ?: "")
                                }
                            }
                        }
                    )
                }
            }
        }

        // Snackbar host
        SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

// Reusable Task Card
@Composable
fun TaskCard(task: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onDelete() },
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = task,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTodoTaskScreen() {
    TodoTaskScreen()
}

