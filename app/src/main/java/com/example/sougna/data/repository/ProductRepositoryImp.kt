package com.example.sougna.data.repository


import com.example.sougna.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

/**
 * Registry containing mock products for demonstration purposes.
 */
class ProductRepositoryImp @Inject constructor() : ProductRepository {

    private val _products = mutableListOf(
        Product(
            id = "1",
            name = "iPhone 15 Pro",
            description = "The latest flagship iPhone with advanced features.",
            price = 999.99,
            userId = "user1",
            rating = 3.5,
            categoryId = "7",
            thumbnailUrl = "https://images.unsplash.com/photo-1695048132832-b41495f12eb4?w=800&auto=format&fit=crop&q=60"
        ),
        Product(
            id = "2",
            name = "MacBook Pro 16-inch",
            description = "A powerful laptop for professionals, featuring the M2 Pro chip.",
            price = 2499.99,
            userId = "user2",
            rating = 2.5,
            categoryId = "7",
            thumbnailUrl = "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=800&auto=format&fit=crop&q=60"
        ),
        Product(
            id = "9",
            name = "Smart Watch",
            description = "Track your fitness and stay connected with this modern smartwatch.",
            price = 299.99,
            userId = "user3",
            rating = 5.0,
            categoryId = "7",
            thumbnailUrl = "https://images.unsplash.com/photo-1544117519-31a4b719223d?w=800&auto=format&fit=crop&q=60"
        ),
        Product(
            id = "10",
            name = "Gaming Headset",
            description = "Immersive gaming headset with noise cancellation.",
            price = 149.99,
            userId = "user4",
            rating = 4.0,
            categoryId = "7",
            thumbnailUrl = "https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?w=800&auto=format&fit=crop&q=60"
        ),
        Product(
            id = "11",
            name = "Wireless Mouse",
            description = "Ergonomic wireless mouse for productivity and gaming.",
            price = 79.99,
            userId = "user5",
            rating = 2.0,
            categoryId = "7",
            thumbnailUrl = "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=800&auto=format&fit=crop&q=60"
        ),

        // Fashion Category
        Product(
            id = "3",
            name = "Leather Jacket",
            description = "A stylish and durable leather jacket for all seasons.",
            price = 199.99,
            userId = "user6",
            categoryId = "2",
            rating = 2.5,
            thumbnailUrl = "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=800&auto=format&fit=crop&q=60"
        ),

        // Pets Category - Updated image
        Product(
            id = "4",
            name = "Premium Dog Food",
            description = "Premium dog food for a healthy and happy pet.",
            price = 49.99,
            rating = 2.0,
            userId = "user7",
            categoryId = "3",
            thumbnailUrl = "https://images.unsplash.com/photo-1568640347023-a616a30bc3bd?w=800&auto=format&fit=crop&q=60"
        ),

        // Travel Category
        Product(
            id = "6",
            name = "Travel Backpack",
            description = "Durable and spacious backpack for all your travel needs.",
            price = 80.99,
            rating = 2.5,
            userId = "user8",
            categoryId = "5",
            thumbnailUrl = "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=800&auto=format&fit=crop&q=60"
        )
    )

    private val _productFlow = MutableSharedFlow<List<Product>>(replay = 1)

    init{
        _productFlow.tryEmit(_products.toList())
    }

    override fun getAllProducts(): Flow<List<Product>> = _productFlow

    override suspend fun addProduct(product: Product) {
        _products.add(product)
        _productFlow.emit(_products.toList())
    }

}
