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
                icon = com.example.sougna.R.drawable.kitchen_24px
            ),
            Category(
                id = "2",
                name = "Fashion",
                description = "Trendy clothing .",
                icon = com.example.sougna.R.drawable.styler_24px
            ),
            Category(
                id = "3",
                name = "Kitchen",
                description = "Everything for your home.",
                icon = com.example.sougna.R.drawable.soup_kitchen_24px
            ),
            Category(
                id = "4",
                name = "Accessories",
                description = "Trendy accessories.",
                icon = com.example.sougna.R.drawable.diamond_24px
            ),
            Category(
                id = "5",
                name = "MyBasket",
                description = "all your holdings.",
                icon = com.example.sougna.R.drawable.baseline_shopping_cart_24
            ),
            Category(
                id = "6",
                name = "Phones",
                description = "Stay ahead with tech that moves at your pace.      ",
                icon = com.example.sougna.R.drawable.smartphone_24px
            ),
            Category(
                id = "7",
                name = "Trendy",
                description = "Discover the latest and most in-demand.",
                icon = com.example.sougna.R.drawable.tag_24px
            ),
            Category(
                id = "8",
                name = "Books",
                description = "Start your journey with us today!"                ,
                icon = com.example.sougna.R.drawable.menu_book_24px
            ),
            Category(
                id = "9",
                name = " Cars",
                description = "Be the driver of luxury and performance!",
                icon = com.example.sougna.R.drawable.directions_car_24px
            ),
            Category(
                id = "10",
                name = "Shipping",
                description = "Quick and reliable delivery time.",
                icon = com.example.sougna.R.drawable.delivery_truck_speed_24px
            )
        )
    }
}
