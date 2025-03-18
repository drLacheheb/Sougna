package com.example.sougna.presentation.view.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sougna.presentation.viewmodel.AddProductEvent
import com.example.sougna.presentation.viewmodel.AddProductState
import com.example.sougna.presentation.viewmodel.AddProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTextFields(viewModel: AddProductViewModel, state: AddProductState) {
    val softRed = Color(0xFFFB5353)
    val lightGray = Color(0xFFD3D3D3)

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedImageUri = uri
        uri?.let { viewModel.onEvent(AddProductEvent.ThumbnailUrlChanged(it.toString())) }
    }

    val textFieldModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = softRed,
        unfocusedBorderColor = lightGray,
        cursorColor = softRed,
        containerColor = Color.White
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        // Name of Product
        Text("Name of Product", color = Color.Black, modifier = Modifier.padding(start = 16.dp, bottom = 4.dp))
        OutlinedTextField(
            value = state.name,
            onValueChange = { viewModel.onEvent(AddProductEvent.NameChanged(it)) },
            placeholder = { Text("Enter Name of product") },
            modifier = textFieldModifier,
            shape = RoundedCornerShape(20.dp),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Description of Product
        Text("Description of Product", color = Color.Black, modifier = Modifier.padding(start = 16.dp, bottom = 4.dp))
        OutlinedTextField(
            value = state.description,
            onValueChange = { viewModel.onEvent(AddProductEvent.DescriptionChanged(it)) },
            placeholder = { Text("Enter Description of product ") },
            modifier = textFieldModifier.height(120.dp),
            shape = RoundedCornerShape(20.dp),
            colors = textFieldColors,
            minLines = 6
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Price of Product
        Text("Price of Product", color = Color.Black, modifier = Modifier.padding(start = 16.dp, bottom = 4.dp))
        OutlinedTextField(
            value = state.price.toString(),
            onValueChange = { viewModel.onEvent(AddProductEvent.PriceChanged(it)) },
            placeholder = { Text("Enter The Price") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = textFieldModifier,
            shape = RoundedCornerShape(20.dp),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Picture of Product
        Text("Picture of Product", color = Color.Black, modifier = Modifier.padding(start = 16.dp, bottom = 4.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            selectedImageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = softRed),
            ) {
                Text("Choose Image")
            }
        }
    }
}
