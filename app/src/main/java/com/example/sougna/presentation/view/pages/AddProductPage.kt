package com.example.sougna.presentation.view.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sougna.presentation.viewmodel.AddProductViewModel
import com.example.sougna.presentation.view.components.ProductTextFields
import com.example.sougna.presentation.view.components.ProductCategoryDropdown
import com.example.sougna.presentation.view.components.ProductActions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductPage(navController: NavController) {
    val viewModel: AddProductViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value

    // Going back to the HomePage
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.popBackStack()
        }
    }

    val primaryRed = Color(0xFFFC5B5B)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Product", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = primaryRed)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = CardDefaults.elevatedShape
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ProductTextFields(viewModel, state)
                    Spacer(modifier = Modifier.height(12.dp))
                    ProductCategoryDropdown(viewModel, state)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            ProductActions(navController, viewModel, state)

            Spacer(modifier = Modifier.height(16.dp))

            if (state.isLoading) {
                CircularProgressIndicator(color = primaryRed)
            }

            state.error?.let {
                Text(text = it, color = primaryRed)
            }
        }
    }
}
