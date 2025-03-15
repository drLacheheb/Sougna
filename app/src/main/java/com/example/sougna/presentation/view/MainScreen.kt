package com.example.sougna.presentation.view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sougna.data.model.Product

@Composable
fun MainScreen(navController: NavHostController) {
    val products = remember { mutableStateListOf<Product>() }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.navigate("addProduct") }) {
            Text(" Add product ")
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(products) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = "السعر: ${product.price}", style = MaterialTheme.typography.bodyMedium)
            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            if (product.imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(product.imageUri),
                    contentDescription = "product's image ",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}