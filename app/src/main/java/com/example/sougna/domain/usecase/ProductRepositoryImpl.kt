package com.example.sougna.domain.usecase

import com.example.sougna.data.model.Product
import com.example.sougna.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor() : ProductRepository {
    private val products = MutableStateFlow<List<Product>>(emptyList())

    override suspend fun addProduct(product: Product) {
        products.value = products.value + product
    }

    override fun getAllProducts(): Flow<List<Product>> {
        return products
    }
}
