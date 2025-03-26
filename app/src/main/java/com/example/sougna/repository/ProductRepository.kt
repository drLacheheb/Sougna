@file:Suppress("UNREACHABLE_CODE")

package com.example.sougna.repository

import com.example.sougna.R
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
                price = 999.99.toString(),
                rating = 4.9,
                userId = "user1",
                categoryId = "1",
                apple = "apple",
                chat = "chat",
                //thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1693009279096",
                imageRes = R.drawable.iphone_15,
            ),
            Product(
                id = "2",
                name = "Samsung",
                description = "A powerful and affordable iPhone.",
                price = 799.99.toString(),
                rating = 4.9,
                userId = "user2",
                categoryId = "1",
                apple = "apple",
                chat = "chat",
                //thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692927227504",
                imageRes = R.drawable.iphone_15,
            ),
            Product(
                id = "3",
                name = "Oppo",
                description = "A powerful and affordable iPhone.",
                price = 799.99.toString(),
                rating = 4.9,
                userId = "user2",
                categoryId = "1",
                apple = "apple",
                chat = "chat",
                //thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692927227504",
                imageRes = R.drawable.iphone_15,
            ),
            Product(
                id = "4",
                name = "iPhone 15",
                description = "A powerful and affordable iPhone.",
                price = 799.99.toString(),
                rating = 4.9,
                userId = "user2",
                categoryId = "1",
                apple = "apple",
                chat = "chat",
                //thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692927227504",
                imageRes = R.drawable.iphone_15,
            ),
            Product(
                id = "5",
                name = "iPhone 15",
                description = "A powerful and affordable iPhone.",
                price = 799.99.toString(),
                rating = 4.9,
                userId = "user2",
                categoryId = "1",
                apple = "apple",
                chat = "chat",
                //thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692927227504",
                imageRes = R.drawable.iphone_15,
            ),
            Product(
                id = "6",
                name = "iPhone 15",
                description = "A powerful and affordable iPhone.",
                price = "799.99",
                rating = 4.9,
                userId = "user2",
                categoryId = "1",
                apple = "apple",
                chat = "chat",
                imageRes = R.drawable.iphone_15
            ),
            Product(
                id = "7",
                name = "iPhone 15",
                description = "A powerful and affordable iPhone.",
                price = 799.99.toString(),
                rating = 4.9,
                userId = "user2",
                categoryId = "1",
                apple = "apple",
                chat = "chat",
                //thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692927227504",
                imageRes = R.drawable.iphone_15,
            ),
            Product(
                id = "8",
                name = "iPhone 15",
                description = "A powerful and affordable iPhone.",
                price = 799.99.toString(),
                rating = 4.9,
                userId = "user2",
                categoryId = "1",
                apple = "apple",
                chat = "chat",
                //thumbnailUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-finish-select-202309-6-1inch?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692927227504",
                imageRes = R.drawable.iphone_15,
            ),
        )
    }
}