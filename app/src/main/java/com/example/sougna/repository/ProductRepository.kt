package com.example.sougna.repository

import com.example.sougna.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

class ProductRepository {

    fun getProducts(): Flow<List<Product>> = flow {
        emit(generateMockProducts())
    }


    private fun generateMockProducts(): List<Product> {
        return listOf(
            Product(
                id = "1",
                name = "الايفون خمسطاشر تفاحة ",
                description = "dont buy it is not worth it is apple",
                price = 41000.0,
                userId = "user1",
                categoryId = "6",
                thumbnailUrl = "https://imgs.search.brave.com/lnjBqWnq1uzVgdS4sT0aAHC_Sq45GLwATY4ao6SsSww/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9mZG4y/LmdzbWFyZW5hLmNv/bS92di9iaWdwaWMv/YXBwbGUtaXBob25l/LTE1LmpwZw",
                rating = 4.9,
                createdAt = Date(),
                updatedAt = Date()
            ),
            Product(
                id = "2",
                name = "Samsung S23 the goat ",
                description = "buyt it ",
                price = 38000.0,
                userId = "user2",
                categoryId = "6",
                thumbnailUrl = "https://images.unsplash.com/photo-1709744722656-9b850470293f?w=500&auto=format&fit=crop&q=60",
                rating = 4.8,
                createdAt = Date(),
                updatedAt = Date()
            ),
            Product(
                id = "3",
                name = "useless laptop called mac",
                description = "its scam Believe me",
                price = 20000.0,
                userId = "user3",
                categoryId = "3",
                thumbnailUrl = "https://images.unsplash.com/photo-1580522154071-c6ca47a859ad?w=500&auto=format&fit=crop&q=60",
                rating = 4.7,
                createdAt = Date(),
                updatedAt = Date()
            ),

            Product(
                id = "4",
                name = "PlayStation 5",
                description = "pc better then consol but i guess you are basic one ",
                price = 4999.0,
                userId = "user4",
                categoryId = "7",
                thumbnailUrl = "https://images.unsplash.com/photo-1606144042614-b2417e99c4e3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8UGxheVN0YXRpb24lMjA1fGVufDB8fDB8fHww",
                rating = 4.9,
                createdAt = Date(),
                updatedAt = Date()
            ),

            Product(
                id = "5",
                name = "Nike Air Max",
                description = "your jordans are fakes ",
                price = 1200.0,
                userId = "user5",
                categoryId = "5",
                thumbnailUrl = "https://imgs.search.brave.com/gjF5RyzvBZluFBewCwjvohN5DZhHUBPB3nQY-FUGMCo/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly92YXJp/ZXR5LmNvbS93cC1j/b250ZW50L3VwbG9h/ZHMvMjAyNS8wMi9O/aWtlLUFpci1Kb3Jk/YW4tMS1SZXRyby1I/aWdoLU9HLUJsYWNr/LVRvZS1GZWF0dXJl/ZC5qcGc_dz0xMDAw/Jmg9NjY3JmNyb3A9/MQ",
                rating = 4.6,
                createdAt = Date(),
                updatedAt = Date()
            ),

            Product(
                id = "6",
                name = "Luxury Apartment",
                description = "تشري شقة في ابليكاسيون وش اقلك راسك",
                price = 150000.0,
                userId = "user6",
                categoryId = "4",
                thumbnailUrl = "https://images.unsplash.com/photo-1580041065738-e72023775cdc?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8THV4dXJ5JTIwQXBhcnRtZW50fGVufDB8fDB8fHww",
                rating = 4.8,
                createdAt = Date(),
                updatedAt = Date()
            )
        )
    }
}
