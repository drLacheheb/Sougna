package com.example.sougna.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sougna.model.Category
import com.example.sougna.repository.CategoryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : ViewModel() {
    // الوصول إلى CategoryRepository بشكل صحيح
    private val categoryRepository = CategoryRepository()

    // قائمة الفئات
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    init {
        loadCategories()
    }

    // دالة لتحميل الفئات من المستودع
    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = categoryRepository.generateMockCategories()
        }
    }
}
