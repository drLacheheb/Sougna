package com.example.sougna.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.model.Category
import com.example.sougna.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Data class representing the state of categories in the application.
 *
 * @property categories List of currently loaded categories
 */
data class CategoryState(
    val categories: List<Category> = emptyList()
)

/**
 * ViewModel responsible for managing category-related data and state.
 *
 * This ViewModel:
 * - Holds and exposes the current state of categories
 * - Provides methods to fetch and update category data
 * - Uses StateFlow for observable state management
 */
class CategoryViewModel : ViewModel() {
    // Internal mutable state flow for category data
    private val _categoryState = MutableStateFlow(CategoryState())
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    // Public immutable state flow exposed to UI components
    val categoryState: StateFlow<CategoryState> = _categoryState.asStateFlow()

    init {
        // Fetch categories when ViewModel is initialized
        fetchCategories()
    }

    /**
     * Fetches categories and updates the state.
     * Uses mock data from MockCategoryRegistry for demonstration.
     */
    private fun fetchCategories() {
        viewModelScope.launch {
            _categoryState.value = _categoryState.value.copy(
                categories = CategoryRepository.generateMockCategories()
            )
        }
    }
}
