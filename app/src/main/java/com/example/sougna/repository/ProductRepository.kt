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
                categoryId = "6",
                thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1693009279096"
            ),
            Product(
                id = "2",
                name = "iPhone 15",
                description = "A powerful and affordable iPhone.",
                price = 799.99,
                userId = "user2",
                categoryId = "6",
                thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692927227504"
            ),Product(
                id = "3",
                name = "Panasonic Washing Machine",
                description = "Panasonic Washing Machine 7KG 1200RPM, Silver, NA-127VB7LEG.",
                price = 876.59,
                userId = "user1",
                categoryId = "1",
                thumbnailUrl = "https://m.media-amazon.com/images/I/611cyf91aUL.__AC_SX300_SY300_QL70_ML2_.jpg"
            ),
            Product(
                id = "4",
                name = "Samsung Refrigerator",
                description = "Samsung Refrigerator 396L - 14CFT - Grey Metal - RT40A3310SA/MR.",
                price = 1023.90,
                userId = "user3",
                categoryId = "1",
                thumbnailUrl = "https://m.media-amazon.com/images/I/61Cqs9zzwGL._AC_SL1500_.jpg"
            )
        )
    }
}
