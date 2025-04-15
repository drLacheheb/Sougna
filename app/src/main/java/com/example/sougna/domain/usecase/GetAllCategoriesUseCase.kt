package com.example.sougna.domain.usecase

import com.example.sougna.data.model.Category
import com.example.sougna.data.repository.CategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> {
        return categoryRepository.getAllCategories()
    }
}