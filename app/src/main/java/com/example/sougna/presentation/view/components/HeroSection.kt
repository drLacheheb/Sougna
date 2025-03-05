package com.example.sougna.presentation.view.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sougna.presentation.viewmodel.CategoryViewModel
import com.example.sougna.presentation.viewmodel.ProductViewModel


@SuppressLint("RememberReturnType")
@Composable
fun HeroSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val productViewModel = hiltViewModel<ProductViewModel>()
        val categoryViewModel = hiltViewModel<CategoryViewModel>()

        val uiState by productViewModel.uiState.collectAsState()
        val categories by categoryViewModel.categoryState.collectAsState()

        Spacer(modifier = Modifier.height(5.dp))

            CategoriesRow(categories.categories)

        Spacer(modifier = Modifier.height(5.dp))

        // Product Grid
        ProductGrid(uiState.products)
    }
}