package com.example.sougna.domain.usecase

import com.example.sougna.data.model.Product
import com.example.sougna.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.getAllProducts()
    }
}
