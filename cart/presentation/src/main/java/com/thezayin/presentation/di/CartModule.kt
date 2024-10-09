package com.thezayin.presentation.di

import com.thezayin.data.CartRepositoryImpl
import com.thezayin.domain.repository.CartRepository
import com.thezayin.domain.usecase.AddProductToCart
import com.thezayin.domain.usecase.AddProductToCartImpl
import com.thezayin.domain.usecase.ClearCartUseCase
import com.thezayin.domain.usecase.ClearCartUseCaseImpl
import com.thezayin.domain.usecase.FetchCartProducts
import com.thezayin.domain.usecase.FetchCartProductsImpl
import com.thezayin.domain.usecase.RemoveProductFromCartUseCase
import com.thezayin.domain.usecase.RemoveProductFromCartUseCaseImpl
import com.thezayin.domain.usecase.UpdateCartProductQuantityUseCase
import com.thezayin.domain.usecase.UpdateCartProductQuantityUseCaseImpl
import com.thezayin.presentation.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cartModule = module {
    factoryOf(::CartRepositoryImpl) bind CartRepository::class
    factoryOf(::FetchCartProductsImpl) bind FetchCartProducts::class
    factoryOf(::ClearCartUseCaseImpl) bind ClearCartUseCase::class
    factoryOf(::AddProductToCartImpl) bind AddProductToCart::class
    factoryOf(::RemoveProductFromCartUseCaseImpl) bind RemoveProductFromCartUseCase::class
    factoryOf(::UpdateCartProductQuantityUseCaseImpl) bind UpdateCartProductQuantityUseCase::class
    viewModelOf(::CartViewModel)
}