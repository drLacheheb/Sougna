package com.example.sougna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.data.model.Product
import com.example.sougna.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ✅ حالة الواجهة (UI State)
data class UIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val products: List<Product> = emptyList()
)

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState()) // ✅ استخدام UIState بدلاً من ProductUiState
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                getAllProductsUseCase().collect { productList ->
                    _uiState.value = _uiState.value.copy(isLoading = false, products = productList)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = "فشل تحميل المنتجات")
            }
        }
    }

    // ✅ إضافة المنتج إلى القائمة وتحديث الواجهة
    fun addProduct(product: Product) {
        println("📥 إضافة المنتج إلى القائمة: $product")
        _uiState.value = _uiState.value.copy(products = _uiState.value.products + product)
    }

}
