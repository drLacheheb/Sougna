package com.example.sougna.model

/**
 * Represents a product category in the application.
 *
 * @property id Unique identifier for the category.
 */
data class Category(
    val id: String,         // إضافة id لتعريف فريد لكل فئة
    val name: String,       // اسم الفئة
    val description: String,// وصف الفئة
    val icon: Int           // أيقونة الفئة
)
