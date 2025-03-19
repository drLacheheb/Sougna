package com.example.sougna.presentation.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sougna.presentation.viewmodel.AddProductViewModel
import com.example.sougna.presentation.view.components.ProductTextFields
import com.example.sougna.presentation.view.components.ProductCategoryDropdown
import com.example.sougna.presentation.viewmodel.AddProductEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductPage(navController: NavController) {
    val viewModel: AddProductViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value

    val primaryRed = Color(0xFFFC5B5B)
    val darkGray = Color(0xFF4E4E4E)
    val lightGray = Color(0xFFD3D3D3)
    val backgroundWhite = Color(0xFFF5F5F5)

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate("home") {
                popUpTo("add_product") { inclusive = true }
            }
        }
    }

    Scaffold(
        containerColor = backgroundWhite
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(primaryRed, RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Add Product", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Fields
            ProductTextFields(viewModel, state)
            Spacer(modifier = Modifier.height(12.dp))

            ProductCategoryDropdown(viewModel, state)
            Spacer(modifier = Modifier.height(12.dp))

            // Buttons
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
            ) {
                Button(
                    onClick = { navController.navigate("home") },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = darkGray)
                ) {
                    Text("Cancel", color = Color.White, fontSize = 16.sp)
                }

                Button(
                    onClick = { viewModel.onEvent(AddProductEvent.Submit) },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryRed)
                ) {
                    Text("Add", color = Color.White, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
