package com.example.sougna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.data.model.Category
import com.example.sougna.domain.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Data class representing the state of categories in the application.
 */
data class CategoryState(
    val categories: List<Category> = emptyList()
)

/**
 * ViewModel responsible for managing category-related data and state.
 */
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : ViewModel() {

    // Internal mutable state flow for category data
    private val _categoryState = MutableStateFlow(CategoryState())

    // Public immutable state flow exposed to UI components
    val categoryState: StateFlow<CategoryState> = _categoryState.asStateFlow()

    init {
        fetchCategories() // ✅ Fetch categories on ViewModel initialization
    }

    /**
     * Fetches categories and updates the state.
     */
    private fun fetchCategories() {
        viewModelScope.launch {
            _categoryState.value = _categoryState.value.copy(
                categories = getAllCategoriesUseCase.invoke()
            )
        }
    }
}
