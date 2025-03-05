package com.example.sougna.presentation.viewmodel

sealed class AddProductEvent {
    data class NameChanged(val name: String) : AddProductEvent()
    data class DescriptionChanged(val description: String) : AddProductEvent()
    data class PriceChanged(val price: String) : AddProductEvent()
    data class CategorySelected(val categoryId: String) : AddProductEvent()
    data class ThumbnailUrlChanged(val url: String) : AddProductEvent()
    object Submit : AddProductEvent()
}
