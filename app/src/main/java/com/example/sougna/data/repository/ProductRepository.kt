package com.example.sougna.data.repository

import com.example.sougna.data.model.Product

interface ProductRepository {

    fun generateMockProducts(): List<Product>

}