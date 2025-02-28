package com.example.sougna.repository

import com.example.sougna.R
import com.example.sougna.model.Category

class CategoryRepository {


    fun generateMockCategories(): List<Category> {
        return listOf(
            Category(
                id = "1",
                name = "Cars",
                description = "Car related products",
                icon = R.drawable.traffic_jam_100px
            ),
            Category(
                id = "2",
                name = "Pieces",
                description = "Pieces related products",
                icon = R.drawable.wheel_100px
            ),
            Category(
                id = "3",
                name = "Informatique",
                description = "Informatics related products",
                icon = R.drawable.windows_client_100px
            ),
            Category(
                id = "4",
                name = "Immobilier",
                description = "Real estate products",
                icon = R.drawable.city_buildings_100px
            ),
            Category(
                id = "5",
                name = "Clothes",
                description = "Clothing and accessories",
                icon = R.drawable.clothes_100px
            ),
            Category(
                id = "6",
                name = "Phones",
                description = "Phones and accessories",
                icon = R.drawable.apple_logo_100px
            ),
            Category(
                id = "7",
                name = "Electronics",
                description = "Electronic gadgets",
                icon = R.drawable.microwave_100px
            ),
            Category(
                id = "8",
                name = "Boutique",
                description = "Boutique related products",
                icon = R.drawable.shopping_basket_100px
            )
        )
    }
}
