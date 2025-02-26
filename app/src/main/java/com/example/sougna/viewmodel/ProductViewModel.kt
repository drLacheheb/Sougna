package com.example.sougna.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.model.Product
import com.example.sougna.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// UI state data class to handle product UI state
data class ProductUIState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    // The state of the products UI
    private val _uiState = MutableStateFlow(ProductUIState(isLoading = true))
    val uiState: StateFlow<ProductUIState> = _uiState

    // To keep the original list of products for filtering
    private var allProducts: List<Product> = emptyList()

    init {
        loadProducts()
    }

    // Function to load products
    fun loadProducts() {
        viewModelScope.launch {
            try {
                _uiState.value = ProductUIState(isLoading = true)
                Log.d("ProductViewModel", "Loading products...")

                // Using collect to gather the data
                productRepository.getProducts().collect { productList ->
                    Log.d("ProductViewModel", "Products loaded: ${productList.size} products found.")
                    allProducts = productList
                    _uiState.value = ProductUIState(products = allProducts)
                }
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error loading products: ${e.message}", e)
                _uiState.value = ProductUIState(error = e.message)
            }
        }
    }

    // Function to filter products by name or category
    fun searchProducts(query: String) {
        Log.d("ProductViewModel", "Searching products with query: $query")

        val filteredProducts = allProducts.filter { product ->
            product.name.contains(query, ignoreCase = true) ||
                    product.categoryId.contains(query, ignoreCase = true)
        }
        Log.d("ProductViewModel", "Found ${filteredProducts.size} products matching the query.")

        _uiState.value = ProductUIState(products = filteredProducts)
    }
}
