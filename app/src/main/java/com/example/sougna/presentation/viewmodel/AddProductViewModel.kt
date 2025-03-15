package com.example.sougna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.data.model.Product
import com.example.sougna.domain.usecase.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val addProductUseCase: AddProductUseCase) : ViewModel() {

    fun addProduct(product: Product) {
        viewModelScope.launch {
            addProductUseCase(product)
        }
    }
}