package com.example.sougna.data.repository

import com.example.sougna.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Registry containing mock products for demonstration purposes.
 */
class ProductRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProductRepository {

    override suspend fun getAllProducts(): List<Product> {
        return try {
            val snapshot = firestore.collection("products").get().await()
            snapshot.toObjects(Product::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addProduct(product: Product): Boolean {
        return try {
            firestore.collection("products").add(product).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
