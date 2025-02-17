package com.example.sougna.data.di

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
object SougnaModule {

    @Provides
    @Singleton
    fun provideProductRepositoryImp(): ProductRepository {
        return ProductRepositoryImp()
    }

    @Provides
    @Singleton
    fun provideCategoryRepositoryImp(): CategoryRepository {
        return CategoryRepositoryImp()
    }

}