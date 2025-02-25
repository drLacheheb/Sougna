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
                icon = com.example.sougna.R.drawable.img
            ),
            Category(
                id = "2",
                name = "Makeup",
                description = "Trendy clothing .",
                icon = com.example.sougna.R.drawable.img_8
            ),
            Category(
                id = "3",
                name = "Home ",
                description = "Everything for your home.",
                icon = com.example.sougna.R.drawable.img_2
            ),
            Category(
                id = "4",
            name = "Accessories ",
            description = "Trendy jewelry and accessories.",
            icon = com.example.sougna.R.drawable.img_3
             ),
            Category(
            id = "5",
            name = "Shoes",
            description = "Trendy and sports shoes for you .",
            icon = com.example.sougna.R.drawable.img_4
            ),
            Category(
            id = "6",
            name = "Pets ",
            description = "Everything for your children.",
            icon = com.example.sougna.R.drawable.img_7
            ),
            Category(
                id = "7",
                name = "Bags  ",
                description = "New collections bags and luggage.",
                icon = com.example.sougna.R.drawable.img_5
            ),
            Category(
                id = "8",
                name = "Toys & Games ",
                description = "All games and kids toys.",
                icon = com.example.sougna.R.drawable.img_6
            )
        )
    }
}