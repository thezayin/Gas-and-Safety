package com.thezayin.presentation.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.data.repository.GetProductsRepositoryImpl
import com.thezayin.domain.repository.GetProductsRepository
import com.thezayin.domain.usecase.GetProWithImg
import com.thezayin.domain.usecase.GetProWithImgImpl
import com.thezayin.domain.usecase.GetProducts
import com.thezayin.domain.usecase.GetProductsImpl
import com.thezayin.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance() }
    factoryOf(::GetProductsRepositoryImpl) bind GetProductsRepository::class
    factoryOf(::GetProductsImpl) bind GetProducts::class
    factoryOf(::GetProWithImgImpl) bind GetProWithImg::class
    viewModelOf(::HomeViewModel)
}