package com.thezayin.presentation.di

import com.thezayin.data.repository.PlaceOrderRepositoryImpl
import com.thezayin.domain.repository.PlaceOrderRepository
import com.thezayin.domain.usecase.PlaceOrder
import com.thezayin.domain.usecase.PlaceOrderImpl
import com.thezayin.presentation.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val orderModule = module {
    viewModelOf(::OrderViewModel)
    factoryOf(::PlaceOrderRepositoryImpl) bind PlaceOrderRepository::class
    factoryOf(::PlaceOrderImpl) bind PlaceOrder::class
}