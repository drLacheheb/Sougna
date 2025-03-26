package com.example.sougna.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.model.Product
import com.example.sougna.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
class ProductViewModel() : ViewModel() {
    // Internal mutable state flow for product data
    private val _uiState = MutableStateFlow(UIState())
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    // Public immutable state flow exposed to UI components
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        // Fetch products when ViewModel is initialized
        fetchProducts()
    }

    /**
     * Fetches products and updates the state.
     * Uses mock data from ProductRegistry with simulated network delay.
     */
    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // Simulate network delay
                delay(2000)
                _uiState.value = _uiState.value.copy(
                    products = ProductRepository.generateMockProducts(),
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
}