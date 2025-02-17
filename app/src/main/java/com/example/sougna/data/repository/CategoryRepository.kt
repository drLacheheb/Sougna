package com.example.sougna.data.repository

import com.example.sougna.data.model.Category

interface CategoryRepository {

    fun generateMockCategories(): List<Category>

}