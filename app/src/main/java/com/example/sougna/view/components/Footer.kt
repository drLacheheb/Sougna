package com.example.sougna.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(color = Color.Gray, thickness = 1.dp) // Separator line
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "© 2024 Sougna. All Rights Reserved.", fontSize = 14.sp, color = Color.Gray)
    }
}