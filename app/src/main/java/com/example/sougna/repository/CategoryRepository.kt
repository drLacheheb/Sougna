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
                name = "Boutiques",
                description = "Latest gadgets and devices.",
                icon = com.example.sougna.R.drawable.shopping_basket_100px
            ),
            Category(
                id = "2",
                name = "Phones",
                description = "Trendy clothing and accessories.",
                icon = com.example.sougna.R.drawable.apple_logo_100px
            ),
            Category(
                id = "3",
                name = "Informatique",
                description = "Everything for your home.",
                icon = com.example.sougna.R.drawable.windows_client_100px
            ),
            Category(
                id = "4",
                name = "Pieces",
                description = "Trendy clothing and accessories.",
                icon = com.example.sougna.R.drawable.wheel_100px
            ),
            Category(
                id = "5",
                name = "Elctronique",
                description = "Trendy clothing and accessories.",
                icon = com.example.sougna.R.drawable.microwave_100px
            ),
            Category(
                id = "6",
                name = "Immbolier",
                description = "Trendy clothing and accessories.",
                icon = com.example.sougna.R.drawable.city_buildings_100px
            ),
            Category(
                id = "7",
                name = "Cars",
                description = "Trendy clothing and accessories.",
                icon = com.example.sougna.R.drawable.traffic_jam_100px
            ),
            Category(
                id = "8",
                name = "Clothes",
                description = "Trendy clothing and accessories.",
                icon = com.example.sougna.R.drawable.clothes_100px
            ),
        )
    }
}
