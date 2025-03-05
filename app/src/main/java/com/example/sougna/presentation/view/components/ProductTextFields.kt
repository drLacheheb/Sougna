package com.example.sougna.presentation.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sougna.presentation.viewmodel.AddProductEvent
import com.example.sougna.presentation.viewmodel.AddProductState
import com.example.sougna.presentation.viewmodel.AddProductViewModel
import androidx.compose.material3.TextFieldDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTextFields(viewModel: AddProductViewModel, state: AddProductState) {
    val softRed = Color(0xFFFB5353)


    OutlinedTextField(
        value = state.name,
        onValueChange = { viewModel.onEvent(AddProductEvent.NameChanged(it)) },
        label = { Text("Product Name") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = softRed,
            cursorColor = softRed,
            focusedLabelColor = softRed,
        )
    )
    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
        value = state.description,
        onValueChange = { viewModel.onEvent(AddProductEvent.DescriptionChanged(it)) },
        label = { Text("Description") },
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = softRed,
            cursorColor = softRed,
            focusedLabelColor = softRed,

        ),
        minLines = 6
    )
    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
        value = state.price.toString(),
        onValueChange = { viewModel.onEvent(AddProductEvent.PriceChanged(it)) },
        label = { Text("Price") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = softRed,
            cursorColor = softRed,
            focusedLabelColor = softRed,

        )
    )
    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
        value = state.thumbnailUrl,
        onValueChange = { viewModel.onEvent(AddProductEvent.ThumbnailUrlChanged(it)) },
        label = { Text("Thumbnail URL") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = softRed,
            cursorColor = softRed,
            focusedLabelColor = softRed,

        )
    )
}
