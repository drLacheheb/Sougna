package com.example.sougna.presentation.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sougna.presentation.viewmodel.AddProductEvent
import com.example.sougna.presentation.viewmodel.AddProductState
import com.example.sougna.presentation.viewmodel.AddProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCategoryDropdown(viewModel: AddProductViewModel, state: AddProductState) {
    var expanded by remember { mutableStateOf(false) }

    val focusedColor = Color(0xFFFB5353)
    val lightGray = Color(0xFFD3D3D3)

    val textFieldModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = focusedColor,
        unfocusedBorderColor = lightGray,
        cursorColor = focusedColor,
        focusedLabelColor = focusedColor,
        containerColor = Color.White
    )

    Box {
        OutlinedTextField(
            value = state.categoryId,
            onValueChange = {},
            label = { Text("Category") },
            readOnly = true,
            modifier = textFieldModifier,
            shape = RoundedCornerShape(20.dp),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            },
            colors = textFieldColors
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listOf("Electronics", "Fashion", "Home").forEach { category ->
                DropdownMenuItem(
                    text = { Text(category) },
                    onClick = {
                        viewModel.onEvent(AddProductEvent.CategorySelected(category))
                        expanded = false
                    }
                )
            }
        }
    }
}
