package com.example.sougna.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.data.model.Product
import com.example.sougna.domain.usecase.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ✅ حالة الشاشة (State)
data class AddProductState(
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val categoryId: Int = 0,
    val imageUri: Uri? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

// ✅ أحداث الشاشة (Events)
sealed class AddProductEvent {
    data class NameChanged(val name: String) : AddProductEvent()
    data class DescriptionChanged(val description: String) : AddProductEvent()
    data class PriceChanged(val price: String) : AddProductEvent()
    data class CategorySelected(val categoryId: Int) : AddProductEvent()
    data class ImageSelected(val uri: Uri?) : AddProductEvent()

    data object Submit : AddProductEvent()
}

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddProductState())
    val state: StateFlow<AddProductState> = _state.asStateFlow()

    private val _isProductAdded = MutableStateFlow(false)
    val isProductAdded: StateFlow<Boolean> = _isProductAdded

    private val _newProduct = MutableStateFlow<Product?>(null)
    val newProduct: StateFlow<Product?> = _newProduct

    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.NameChanged -> _state.value = _state.value.copy(name = event.name)
            is AddProductEvent.DescriptionChanged -> _state.value = _state.value.copy(description = event.description)
            is AddProductEvent.PriceChanged -> _state.value = _state.value.copy(price = event.price)
            is AddProductEvent.CategorySelected -> _state.value = _state.value.copy(categoryId = event.categoryId)
            is AddProductEvent.ImageSelected -> _state.value = _state.value.copy(imageUri = event.uri)
            is AddProductEvent.Submit -> addProduct()
        }
    }

    private fun addProduct() {
        val currentState = _state.value
        println("🚀 محاولة إضافة المنتج: $currentState")

        if (currentState.name.isEmpty() || currentState.price.isEmpty() || currentState.categoryId == 0) {
            _state.value = _state.value.copy(error = "الرجاء ملء جميع الحقول")
            println("⚠️ لم يتم ملء جميع الحقول!")
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val newProduct = Product(
                id = System.currentTimeMillis().toString(),
                name = currentState.name,
                description = currentState.description,
                price = currentState.price.toDoubleOrNull() ?: 0.0, // ✅ تأكد من التحويل
                thumbnailUrl = currentState.imageUri?.toString() ?: "",
                rating = 4.5
            )

            println("📌 المنتج الجديد: $newProduct")

            val result: Boolean = addProductUseCase(newProduct)
            println("🔄 نتيجة الإضافة: $result")

            _newProduct.value = newProduct
            _isProductAdded.value = result

            _state.value = _state.value.copy(
                isLoading = false,
                isSuccess = result,
                error = if (result) null else "فشل في إضافة المنتج"
            )
        }
    }

}
