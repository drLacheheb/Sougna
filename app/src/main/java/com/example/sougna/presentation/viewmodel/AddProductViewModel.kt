package com.example.sougna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.data.model.Product
import com.example.sougna.domain.usecase.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(AddProductState())
    val state: StateFlow<AddProductState> = _state.asStateFlow()


    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.NameChanged -> {
                _state.update { it.copy(name = event.name) }
            }
            is AddProductEvent.DescriptionChanged -> {
                _state.update { it.copy(description = event.description) }
            }
            is AddProductEvent.PriceChanged -> {
                val price = event.price.toDoubleOrNull() ?: 0.0
                _state.update { it.copy(price = price) }
            }
            is AddProductEvent.CategorySelected -> {
                _state.update { it.copy(categoryId = event.categoryId) }
            }
            is AddProductEvent.ThumbnailUrlChanged -> {
                _state.update { it.copy(thumbnailUrl = event.url) }
            }
            is AddProductEvent.Submit -> {
                submitProduct()
            }
        }
    }


    private fun submitProduct() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, isSuccess = false) }

            val validationError = validateInputs()
            if (validationError != null) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = validationError
                    )
                }
                return@launch
            }

            try {


                val product = Product(
                    id = UUID.randomUUID().toString(),
                    name = state.value.name,
                    description = state.value.description,
                    price = state.value.price,
                    categoryId = state.value.categoryId,
                    thumbnailUrl = state.value.thumbnailUrl,
                    createdAt = Date(),
                    updatedAt = Date(),
                    userId = "",
                    rating = 0.0
                )


                addProductUseCase(product)


                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        name = "",
                        description = "",
                        price = 0.0,
                        categoryId = "",
                        thumbnailUrl = ""
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Failed to add product: ${e.localizedMessage}"
                    )
                }
            }
        }
    }


    private fun validateInputs(): String? {
        val currentState = state.value
        return when {
            currentState.name.isBlank() -> "Product name is required"
            currentState.description.isBlank() -> "Description is required"
            currentState.price <= 0 -> "Price must be greater than 0"
            currentState.categoryId.isBlank() -> "Please select a category"
            currentState.thumbnailUrl.isBlank() -> "Thumbnail URL is required"
            else -> null
        }
    }
}

