package com.example.sougna.presentation.view.buttons.ProfileButton

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SaveButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373)),
        modifier = modifier
    ) {
        Text("Save", color = Color.White)
    }
}
