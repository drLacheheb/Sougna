package com.example.sougna.data.network

// Network.kt
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*

object ApiClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true  // Ignore extra fields from the server
                isLenient = true           // Accept non-strict JSON
                prettyPrint = true
            })
        }
    }
}
