package com.example.sougna.presentation.view.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sougna.presentation.view.components.Footer
import com.example.sougna.presentation.view.components.HeroSection
import com.example.sougna.presentation.view.components.Navbar

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun HomePage(navController: NavHostController) {
    val products = listOf("Product 1", "Product 2", "Product 3")
    val filteredProducts = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        filteredProducts.addAll(products) // تهيئة القائمة بجميع المنتجات
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Navbar(
                onSearch = { query ->
                    println("Search query: $query")
                    filteredProducts.clear()
                    if (query.isEmpty()) {
                        filteredProducts.addAll(products)
                    } else {
                        filteredProducts.addAll(products.filter { it.contains(query, ignoreCase = true) })
                    }
                }
            )

            HeroSection()

            LazyColumn {
                items(filteredProducts) { product ->
                    Text(
                        text = product,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }

            Footer()
        }

        FloatingActionButton(
            onClick = { navController.navigate("addProduct") },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Product")
        }
    }
}