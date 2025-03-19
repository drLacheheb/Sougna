package com.example.sougna.presentation.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.runtime.Composable

@Composable

fun AddProductButton(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate("add_product") },
        containerColor = Color(0xFF21D4F3),
        contentColor = Color.White
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add Product")
    }
}
