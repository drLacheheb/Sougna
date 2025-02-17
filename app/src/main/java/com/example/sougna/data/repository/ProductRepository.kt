package com.example.sougna.data.repository

import com.example.sougna.data.model.Product

interface ProductRepository {

    suspend fun getAllProducts(): List<Product>
    suspend fun addProduct(product: Product): Boolean

}