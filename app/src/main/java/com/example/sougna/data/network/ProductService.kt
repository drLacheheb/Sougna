package com.example.sougna.data.network

import com.example.sougna.data.model.Product
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

object ProductService {
    suspend fun fetchProducts(): List<Product> {
        return ApiClient.client.get("http://10.0.2.2:3000/api/products").body()
    }
}
