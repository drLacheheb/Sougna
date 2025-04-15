package com.example.sougna.presentation.view.TextFeild.Product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sougna.presentation.viewmodel.AddProductEvent
import com.example.sougna.presentation.viewmodel.AddProductViewModel

@Composable
fun NameField(
    modifier: Modifier = Modifier,
    viewModel: AddProductViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val name = state.name

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.onEvent(AddProductEvent.NameChanged(it)) },
            label = { Text("Name of Product") }, // ✅ Fixed label issue
            placeholder = { Text("Enter Name of Product") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}


