package com.thezayin.presentation.di

import com.thezayin.data.repository.HistoryRepositoryImpl
import com.thezayin.domain.repository.HistoryRepository
import com.thezayin.domain.usecase.GetUserOrders
import com.thezayin.domain.usecase.GetUserOrdersImpl
import com.thezayin.presentation.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val historyModule = module {
    viewModelOf(::HistoryViewModel)
    factoryOf(::HistoryRepositoryImpl) bind HistoryRepository::class
    factoryOf(::GetUserOrdersImpl) bind GetUserOrders::class
}