package com.example.sougna.domain.usecase

import com.example.sougna.data.model.Product
import com.example.sougna.data.repository.ProductRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return productRepository.getAllProducts()
    }
}
