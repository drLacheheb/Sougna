package com.example.sougna.data.repository

import com.example.sougna.R
import com.example.sougna.data.model.Category
import javax.inject.Inject

/**
 * Registry containing mock categories for demonstration purposes.
 */
class CategoryRepositoryImp @Inject constructor(): CategoryRepository {
    /**
     * Generates a list of mock categories.
     *
     * @return List of Category objects with sample data
     */
    override fun generateMockCategories(): List<Category> {
        return listOf(
            Category(
                id = "1",
                name = "Electronics",
                description = "Latest gadgets and devices.",
                icon = R.drawable.ic_electronics
            ),
            Category(
                id = "2",
                name = "Fashion",
                description = "Trendy clothing and accessories.",
                icon = R.drawable.ic_fashion
            ),
            Category(
                id = "3",
                name = "Home & Kitchen",
                description = "Everything for your home.",
                icon = R.drawable.ic_home_kitchen
            )
        )
    }
}
