package com.example.sougna.presentation.view.buttons.ProductButton

import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CancelButton(navController: NavController, modifier: Modifier = Modifier) {
    Button(
        onClick = { navController.navigate("home") }, // Go back to previous screen
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
        modifier = modifier.height(45.dp) // No weight() here
    ) {
        Text("Cancel", color = Color.White)
    }
}