package com.example.sougna.presentation.view.TextFeild.Profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameProfile(
    Name: String,
    onName: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = Name,
        onValueChange = onName,
        label = { Text("First Name") }, // Floating label effect
        placeholder = { Text("Enter Your Name") }, // Placeholder when no input
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue, // Border color when focused
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Blue // Label color when focused
        )
    )
}

