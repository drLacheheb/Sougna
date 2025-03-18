package com.example.sougna.presentation.viewmodel

data class AddProductState(
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val categoryId: String = "",
    val thumbnailUrl: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)
