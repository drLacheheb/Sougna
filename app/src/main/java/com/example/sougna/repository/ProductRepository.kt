package com.example.sougna.repository

import com.example.sougna.model.Product

/**
 * Registry containing mock products for demonstration purposes.
 */
object ProductRepository {
    /**
     * Generates a list of mock products.
     *
     * @return List of Product objects with sample data
     */
    fun generateMockProducts(): List<Product> {
        return listOf(
            Product(
                id = "1",
                name = "iPhone 15 Pro",
                description = "The latest flagship iPhone with advanced features.",
                price = 999.99,
                userId = "user1",
                categoryId = "1",
                thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1693009279096"
            ),
            Product(
                id = "2",
                name = "iPhone 15",
                description = "A powerful and affordable iPhone.",
                price = 799.99,
                userId = "user2",
                categoryId = "1",
                thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692927227504"
            )
        )
    }
}
