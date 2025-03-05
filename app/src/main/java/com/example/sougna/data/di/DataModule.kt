package com.example.sougna.data.di

import android.content.Context
import com.example.sougna.data.repository.CategoryRepository
import com.example.sougna.data.repository.CategoryRepositoryImp
import com.example.sougna.data.repository.ProductRepository
import com.example.sougna.data.repository.ProductRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository {
        return ProductRepositoryImp()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepositoryImp()
    }


}