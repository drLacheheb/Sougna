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
                name = "Home",
                description = "",
                icon = "https://img.icons8.com/?size=100&id=zUlQIRAWidll&format=png&color=000000"
            ),
            Category(
                id = "2",
                name = "Fashion",
                description = "Trendy clothing and accessories.",
                icon = "https://img.icons8.com/external-obivous-color-kerismaker/48/external-cleaning-laundry-color-obivous-color-kerismaker-31.png"
            ),
            Category(
                id = "3",
                name = "Pets",
                description = "",
                icon = "https://img.icons8.com/?size=100&id=yln7W1tiSYJz&format=png&color=000000"
            ),
            Category(
                id = "4",
                name = "Health",
                description = "",
                icon = "https://img.icons8.com/?size=100&id=51819&format=png&color=000000" // Add drawable resource
            ),
            Category(
                id = "5",
                name = "Travel",
                description = "",
                icon = "https://img.icons8.com/?size=100&id=qC_dhM0a7ebN&format=png&color=000000" // Add drawable resource
            ),
            Category(
                id = "7",
                name = "Electronics",
                description = "",
                icon = "https://img.icons8.com/?size=100&id=13352&format=png&color=000000" // Add drawable resource
            ),
            Category(
                id = "8",
                name = "Cars",
                description = "Vehicles and automotive parts.",
                icon = "https://img.icons8.com/?size=100&id=21009&format=png&color=000000" // Add drawable resource
            ),
            Category(
                id = "9",
                name = "Others",
                description = "Miscellaneous items.",
                icon = "https://img.icons8.com/?size=100&id=38099&format=png&color=000000"
            )

        )
    }
}
