package com.example.sougna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.data.model.Product
import com.example.sougna.domain.usecase.AddProductUseCase
import com.example.sougna.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Data class representing the UI state for products.
 *
 * @property isLoading Indicates if data is currently being loaded
 * @property products List of currently loaded products
 * @property error Error message if any occurred during data fetching
 */
data class UIState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

/**
 * ViewModel responsible for managing product-related data and state.
 *
 * This ViewModel:
 * - Holds and exposes the current state of products
 * - Provides methods to fetch and update product data
 * - Uses StateFlow for observable state management
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {
    // Internal mutable state flow for product data
    private val _uiState = MutableStateFlow(UIState())

    // Public immutable state flow exposed to UI components
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        // Fetch products when ViewModel is initialized
        fetchProducts()
    }

    /**
     * Fetches products and updates the state.
     */
    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // Fetch products using the use case
                _uiState.value = _uiState.value.copy(
                    products = getAllProductsUseCase(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    /**
     * Adds a new product and updates the state.
     * @param product The product to be added
     */
    fun addProduct(product: Product) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                addProductUseCase(product)
                // Refresh the product list after successful addition
                fetchProducts()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Failed to add product",
                    isLoading = false
                )
            }
        }
    }
}
