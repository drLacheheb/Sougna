package com.example.sougna.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.data.model.Product
import com.example.sougna.domain.usecase.AddProductUseCase
import com.example.sougna.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class UIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val products: List<Product> = emptyList()
)

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val addProductUseCase: AddProductUseCase // ✅ Inject use case
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        fetchProducts() // ✅ Load products when ViewModel is created
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getAllProductsUseCase().catch {
                _uiState.value = _uiState.value.copy(errorMessage = it.message, isLoading = false)
            }.collect { products ->
                _uiState.value = _uiState.value.copy(products = products, isLoading = false)
            }
        }
    }

    // ✅ Add Product and Refresh Product List
    fun addProduct(name: String, description: String, price: String, imageUri: Uri?, onSuccess: () -> Unit) {
        val priceValue = price.toDoubleOrNull() ?: return

        val newProduct = Product(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description,
            price = priceValue,
            thumbnailUrl = imageUri?.toString() ?: "",
            rating = 4.5
        )

        viewModelScope.launch {
            try {
                addProductUseCase(newProduct) // ✅ Add product
                fetchProducts() // ✅ Refresh product list after adding
                onSuccess() // ✅ Navigate to home
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message)
            }
        }
    }
}
