package com.example.sougna.presentation.view.state

import com.example.sougna.data.model.Product

data class ProductUiState(
    val products: List<Product> = emptyList()
)
