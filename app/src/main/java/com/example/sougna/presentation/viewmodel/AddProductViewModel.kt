package com.example.sougna.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.sougna.data.model.Product
import com.example.sougna.domain.usecase.AddProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddProductState())
    val state: StateFlow<AddProductState> = _state.asStateFlow()

    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.NameChanged -> updateState { it.copy(name = event.name) }
            is AddProductEvent.DescriptionChanged -> updateState { it.copy(description = event.description) }
            is AddProductEvent.PriceChanged -> handlePriceChange(event.price)
            is AddProductEvent.CategorySelected -> updateState { it.copy(categoryId = event.categoryId) }
            is AddProductEvent.ImageUrlChanged -> updateState { it.copy(imageUrl = event.url) }
            is AddProductEvent.Submit -> submitProduct()
        }
    }

    private fun updateState(transform: (AddProductState) -> AddProductState) {
        _state.value = transform(_state.value)
    }

    private fun handlePriceChange(price: String) {
        val parsedPrice = price.toDoubleOrNull()?.takeIf { it > 0 } ?: 0.0
        updateState { it.copy(price = parsedPrice) }
    }

    private fun submitProduct() {
        val currentState = _state.value
        if (!validateFields(currentState)) return

        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null, isSuccess = false) }
            try {
                val result = addProductUseCase(
                    Product(
                        id = UUID.randomUUID().toString(), // Generating a random ID
                        name = currentState.name,
                        description = currentState.description,
                        price = currentState.price,
                        rating = 0.0, // Default rating
                        userId = "defaultUserId", // Replace with actual userId
                        categoryId = currentState.categoryId.toString(), // Ensure type matches
                        thumbnailUrl = currentState.imageUrl, // Assuming thumbnailUrl is the same as imageUrl
                        createdAt = Date(),
                        updatedAt = Date()
                    )
                )
                updateState { it.copy(isLoading = false, isSuccess = true, error = null) }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, error = e.localizedMessage) }
            }
        }
    }

    private fun validateFields(state: AddProductState): Boolean {
        return when {
            state.name.isBlank() -> showError("Name is required")
            state.description.isBlank() -> showError("Description is required")
            state.price <= 0.0 -> showError("Invalid price")
            state.categoryId == 0 -> showError("Category must be selected")
            state.imageUrl.isBlank() -> showError("Image URL is required")
            else -> true
        }
    }

    private fun showError(message: String): Boolean {
        updateState { it.copy(error = message) }
        return false
    }
}

// State class representing the UI state of adding a product
data class AddProductState(
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val categoryId: Int = 0,
    val imageUrl: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

// Events that can be triggered
sealed class AddProductEvent {
    data class NameChanged(val name: String) : AddProductEvent()
    data class DescriptionChanged(val description: String) : AddProductEvent()
    data class PriceChanged(val price: String) : AddProductEvent()
    data class CategorySelected(val categoryId: Int) : AddProductEvent()
    data class ImageUrlChanged(val url: String) : AddProductEvent()
    object Submit : AddProductEvent()
}