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

val homeModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance() }
    factoryOf(::ProductRepositoryImpl) bind ProductRepository::class
    factoryOf(::FetchProductsUseCaseImpl) bind FetchProductsUseCase::class
    factoryOf(::FetchProductsWithImagesUseCaseImpl) bind FetchProductsWithImagesUseCase::class
    viewModelOf(::HomeViewModel)
}