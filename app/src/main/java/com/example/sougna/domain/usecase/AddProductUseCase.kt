package com.example.sougna.domain.usecase

import com.example.sougna.data.model.Product
import com.example.sougna.data.repository.ProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product): Boolean {
        return try {
            productRepository.addProduct(product)
            println("✅ المنتج أُضيف بنجاح إلى المستودع!")
            true
        } catch (e: Exception) {
            println("❌ خطأ أثناء إضافة المنتج: ${e.message}")
            false
        }
    }
}
