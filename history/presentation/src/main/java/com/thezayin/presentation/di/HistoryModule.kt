package com.thezayin.presentation.di

import com.thezayin.data.repository.MyOrderRepositoryImpl
import com.thezayin.domain.repository.MyOrdersRepository
import com.thezayin.domain.usecase.GetMyOrders
import com.thezayin.domain.usecase.GetMyOrdersImpl
import com.thezayin.presentation.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val historyModule = module {
    viewModelOf(::HistoryViewModel)
    factoryOf(::MyOrderRepositoryImpl) bind MyOrdersRepository::class
    factoryOf(::GetMyOrdersImpl) bind GetMyOrders::class
}