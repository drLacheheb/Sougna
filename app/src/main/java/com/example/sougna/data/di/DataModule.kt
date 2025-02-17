package com.example.sougna.data.di

import android.content.Context
import com.example.sougna.data.repository.CategoryRepository
import com.example.sougna.data.repository.CategoryRepositoryImp
import com.example.sougna.data.repository.ProductRepository
import com.example.sougna.data.repository.ProductRepositoryImp
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideProductRepository(firestore: FirebaseFirestore): ProductRepository {
        return ProductRepositoryImp(firestore)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepositoryImp()
    }

    @Provides
    @Singleton
    fun provideFirebaseApp(@ApplicationContext context: Context): FirebaseApp {
        // Initialize FirebaseApp with the application context.
        return FirebaseApp.initializeApp(context)
            ?: throw IllegalStateException("FirebaseApp initialization failed")
    }

    @Provides
    @Singleton
    fun provideFirestore(firebaseApp: FirebaseApp): FirebaseFirestore {

        val firestore = FirebaseFirestore.getInstance(firebaseApp)
        val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
        firestore.firestoreSettings = settings
        return firestore
    }

}