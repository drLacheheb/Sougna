package com.example.sougna.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import com.example.sougna.data.model.Product
import com.example.sougna.presentation.viewmodel.ProductViewModel
import java.util.UUID

@Composable
fun AddProductScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    productViewModel: ProductViewModel
) {
    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = productDescription,
            onValueChange = { productDescription = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = productQuantity,
            onValueChange = { productQuantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val product = Product(
                    name = productName,
                    description = productDescription,
                    price = productPrice.toDoubleOrNull() ?: 0.0,
                    id = UUID.randomUUID().toString(),
                    userId = "1",
                    categoryId = "1",
                    thumbnailUrl = "https://picsum.photos/id/237/200/300"
                )
                productViewModel.addProduct(product)
                onBackClick()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Product")
        }

        if (productViewModel.uiState.value.isLoading) {
            Text("Adding product...")
        }

        productViewModel.uiState.value.error?.let { error ->
            Text("Error: $error", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Return")
        }
    }
}
