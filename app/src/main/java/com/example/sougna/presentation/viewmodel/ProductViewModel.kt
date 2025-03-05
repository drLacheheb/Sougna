package com.example.sougna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.data.model.Product
import com.example.sougna.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


data class UIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val products: List<Product> = emptyList()
)


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())


    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

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

    init{
        fetchProducts()
    }

}
