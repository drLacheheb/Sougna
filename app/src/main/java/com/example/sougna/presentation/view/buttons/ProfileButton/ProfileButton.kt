package com.example.sougna.presentation.view.buttons.ProfileButton

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProfileButton(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(
        onClick = { navController.navigate("profile") }, // ✅ Ensure the route name matches AppNavigator
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        modifier = modifier
            .padding(top = 16.dp) // Adjust spacing
            .width(140.dp) // Set width
            .height(45.dp) // Set height
    ) {
        Text("My Profile", color = Color.Black)
    }
}