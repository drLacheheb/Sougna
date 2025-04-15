package com.example.sougna.domain.di

import com.example.sougna.data.repository.CategoryRepository
import com.example.sougna.data.repository.ProductRepository
import com.example.sougna.domain.usecase.AddProductUseCase
import com.example.sougna.domain.usecase.GetAllCategoriesUseCase
import com.example.sougna.domain.usecase.GetAllProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideAddProductUseCase(productRepository: ProductRepository): AddProductUseCase {
        return AddProductUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(productRepository: ProductRepository): GetAllProductsUseCase {
        return GetAllProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllCategoriesUseCase(categoryRepository: CategoryRepository): GetAllCategoriesUseCase {
        return GetAllCategoriesUseCase(categoryRepository)
    }
}