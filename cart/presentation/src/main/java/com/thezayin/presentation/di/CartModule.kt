package com.thezayin.presentation.di

import com.thezayin.data.CartProRepositoryImpl
import com.thezayin.domain.repository.CartProRepository
import com.thezayin.domain.usecase.AddToCart
import com.thezayin.domain.usecase.AddToCartImpl
import com.thezayin.domain.usecase.DeleteAllCart
import com.thezayin.domain.usecase.DeleteAllCartImpl
import com.thezayin.domain.usecase.DeleteFromCart
import com.thezayin.domain.usecase.DeleteFromCartImpl
import com.thezayin.domain.usecase.GetCartProducts
import com.thezayin.domain.usecase.GetCartProductsImpl
import com.thezayin.domain.usecase.UpdateQuantity
import com.thezayin.domain.usecase.UpdateQuantityImpl
import com.thezayin.presentation.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cartModule = module {
    factoryOf(::CartProRepositoryImpl) bind CartProRepository::class
    factoryOf(::GetCartProductsImpl) bind GetCartProducts::class
    factoryOf(::DeleteAllCartImpl) bind DeleteAllCart::class
    factoryOf(::AddToCartImpl) bind AddToCart::class
    factoryOf(::DeleteFromCartImpl) bind DeleteFromCart::class
    factoryOf(::UpdateQuantityImpl) bind UpdateQuantity::class
    viewModelOf(::CartViewModel)
}