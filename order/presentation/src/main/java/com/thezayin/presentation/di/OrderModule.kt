package com.thezayin.presentation.di

import com.thezayin.data.repository.OrderRepositoryImpl
import com.thezayin.domain.repository.OrderRepository
import com.thezayin.domain.usecase.CreateOrder
import com.thezayin.domain.usecase.CreateOrderImpl
import com.thezayin.presentation.OrderViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val orderModule = module {
    viewModelOf(::OrderViewModel)
    // Provide Ktor HttpClient
    single {
        HttpClient(CIO) {
            // Customize the HttpClient if needed
        }
    }

    factoryOf(::OrderRepositoryImpl) bind OrderRepository::class
    factoryOf(::CreateOrderImpl) bind CreateOrder::class
}