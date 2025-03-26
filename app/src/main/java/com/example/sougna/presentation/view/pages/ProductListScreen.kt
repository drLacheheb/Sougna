package com.example.sougna.presentation.view.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sougna.presentation.viewmodel.AddProductState
import com.example.sougna.presentation.viewmodel.AddProductViewModel

@Composable
fun ProductListScreen(viewModel: AddProductViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    LazyColumn {
        item {
            if (state.isLoading) {
                Text(text = "Loading...", modifier = Modifier.padding(16.dp))
            }
        }
        item {
            state.error?.let {
                Text(text = "Error: $it", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
            }
        }
        items(listOf(state)) { productState ->
            ProductItem(productState)
        }
    }
}

@Composable
fun ProductItem(state: AddProductState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = state.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = state.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Price: $${state.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
