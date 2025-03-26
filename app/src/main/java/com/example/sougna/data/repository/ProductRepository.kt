package com.example.sougna.data.repository

import com.example.sougna.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<List<Product>>
    suspend fun addProduct(product: Product)

}