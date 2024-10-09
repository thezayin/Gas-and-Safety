package com.thezayin.presentation.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.data.repository.ProductRepositoryImpl
import com.thezayin.domain.repository.ProductRepository
import com.thezayin.domain.usecase.FetchProductsUseCase
import com.thezayin.domain.usecase.FetchProductsUseCaseImpl
import com.thezayin.domain.usecase.FetchProductsWithImagesUseCase
import com.thezayin.domain.usecase.FetchProductsWithImagesUseCaseImpl
import com.thezayin.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module that provides dependencies for Firebase services, repository implementations,
 * use cases, and the HomeViewModel.
 */
val homeModule = module {

    // Provide a singleton instance of FirebaseFirestore
    single { FirebaseFirestore.getInstance() }

    // Provide a singleton instance of FirebaseAuth
    single { FirebaseAuth.getInstance() }

    // Provide a singleton instance of FirebaseStorage
    single { FirebaseStorage.getInstance() }

    // Provide an implementation of ProductRepository using ProductRepositoryImpl
    // ProductRepositoryImpl is created as a factory and bound to ProductRepository interface
    factoryOf(::ProductRepositoryImpl) bind ProductRepository::class

    // Provide an implementation of FetchProductsUseCase using FetchProductsUseCaseImpl
    // This is a factory, meaning a new instance is created each time it is needed
    factoryOf(::FetchProductsUseCaseImpl) bind FetchProductsUseCase::class

    // Provide an implementation of FetchProductsWithImagesUseCase using FetchProductsWithImagesUseCaseImpl
    factoryOf(::FetchProductsWithImagesUseCaseImpl) bind FetchProductsWithImagesUseCase::class

    // Provide HomeViewModel as a ViewModel
    viewModelOf(::HomeViewModel)
}
