package com.example.sougna.presentation.view.pages

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun UserProductAdded(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(
        onClick = { navController.navigate("addProduct") }, // ✅ Ensure route matches navigation
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        modifier = modifier
            .width(140.dp) // Set width
            .height(45.dp) // Set height
    ) {
        Text("My Product", color = Color.Black)
    }
}
