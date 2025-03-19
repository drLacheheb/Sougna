package com.example.sougna.presentation.view.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.sougna.presentation.view.components.Footer
import com.example.sougna.presentation.view.components.HeroSection
import com.example.sougna.presentation.view.components.Navbar
import com.example.sougna.presentation.view.components.ProductGrid
import com.example.sougna.presentation.viewmodel.ProductViewModel

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun HomePage(navController: NavHostController, productViewModel: ProductViewModel) {
    val uiState by productViewModel.uiState.collectAsState()

    // 🔄 تحديث المنتجات عند الرجوع من صفحة الإضافة
    LaunchedEffect(Unit) {
        productViewModel.loadProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Navbar()
        HeroSection(navController = navController)

        if (uiState.products.isNotEmpty()) {
            ProductGrid(products = uiState.products, navController = navController)
        } else {
            Text(text = "No products available", color = Color.Gray)
        }

        Footer()
    }
}
