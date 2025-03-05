package com.example.sougna.domain.usecase

import com.example.sougna.data.model.Product
import com.example.sougna.data.repository.ProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        return productRepository.addProduct(product)
    }
}
