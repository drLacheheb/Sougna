package com.example.sougna.repository

import com.example.sougna.model.Category

/**
 * Registry containing mock categories for demonstration purposes.
 */
object CategoryRepository {
    /**
     * Generates a list of mock categories.
     *
     * @return List of Category objects with sample data
     */
    fun generateMockCategories(): List<Category> {
        return listOf(
            Category(
                id = "1",
                name = "Electronics",
                description = "Latest gadgets and devices.",
                icon = com.example.sougna.R.drawable.ic_electronics
            ),
            Category(
                id = "2",
                name = "Fashion",
                description = "Trendy clothing and accessories.",
                icon = com.example.sougna.R.drawable.ic_fashion
            ),
            Category(
                id = "3",
                name = "Home & Kitchen",
                description = "Everything for your home.",
                icon = com.example.sougna.R.drawable.ic_home_kitchen
            )
        )
    }
}
