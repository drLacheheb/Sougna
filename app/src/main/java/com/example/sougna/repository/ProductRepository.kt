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
                thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqZN7qsczCC-Kxu8bZRFWuUjCvwiQaurJOVA&s" ,

            ),
            Product(
                id = "2",
                name = "iPhone 15",
                description = "A powerful and affordable iPhone.",
                price = 799.99,
                userId = "user2",
                categoryId = "1",
                thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIrFKc4CpNq3xAcWozgD8KdzzHI8mZrfIPVg&s"
            ),
            Product(
                id = "3",
            name = "Tote Bag",
            description = "Large Capacity Tote Bag For Women, Casual Commuter Crossbody Bag, Luxury Brand Handbag, 2025 New Model.",
            price = 45.99,
            userId = "user3",
            categoryId = "1",
            thumbnailUrl = "https://i.pinimg.com/736x/7f/cc/74/7fcc7438de12431baa8db21c7d5e65b9.jpg"
        ),
        Product(
            id = "4",
            name = "Headphones",
            description = "Open Ear Headphones True Wireless Headphone, 400mAh Battery & Ultra Playtime, Ear Clip Earbuds Built-In Mic With Ear Hooks, IPX5 Sport Earphones For Music.",
            price = 20.3,
            userId = "user4",
            categoryId = "1",
            thumbnailUrl = "https://i.pinimg.com/736x/55/a8/04/55a8040b27b707747534297f70dbf1af.jpg"
        ),
        Product(
            id = "5",
            name = "Watch",
            description = "CARYINO 1pc CARYINO Women's Stainless Steel Strap Watch With Gold & Silver Dual-Tone, Water Drop Shaped Dial, Embedded Zirconia Stones, Classic Quartz Business Watch For Daily, Gathering, And Evening Events.",
            price = 56.17,
            userId = "user5",
            categoryId = "1",
            thumbnailUrl = "https://i.pinimg.com/736x/2e/f9/5a/2ef95a0bfa4b0f17fc827ebfcb11f174.jpg"
        ),
        Product(
            id = "6",
            name = "Glasses ",
            description = "2pcs Men Classic Personality Classy Aesthetic Fashion Glasses Combination For Daily Life Rave Party Musical Festival Decoration.",
            price = 88.7,
            userId = "user6",
            categoryId = "1",
            thumbnailUrl = "https://img.ltwebstatic.com/images3_spmp/2024/07/05/be/1720176323847d9254d0d05d7343eedcdd4b30937b_thumbnail_720x.webp"
        )
        )
    }
}