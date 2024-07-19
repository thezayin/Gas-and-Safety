package com.thezayin.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.databases.di.provideCartDao
import com.thezayin.databases.di.provideCartDatabase
import com.thezayin.databases.di.provideProfileDao
import com.thezayin.framework.remote.RemoteConfig
import com.thezayin.useraddress.data.repository.AreaRepositoryImpl
import com.thezayin.useraddress.data.repository.ProfileRepositoryImpl
import com.thezayin.useraddress.domain.repository.AreaRepository
import com.thezayin.useraddress.domain.repository.ProfileRepository
import com.thezayin.useraddress.domain.usecase.AddProfile
import com.thezayin.useraddress.domain.usecase.AddProfileImpl
import com.thezayin.useraddress.domain.usecase.DeleteAllProfiles
import com.thezayin.useraddress.domain.usecase.DeleteAllProfilesImpl
import com.thezayin.useraddress.domain.usecase.DeleteProfileById
import com.thezayin.useraddress.domain.usecase.DeleteProfileByIdImpl
import com.thezayin.useraddress.domain.usecase.GetAllProfiles
import com.thezayin.useraddress.domain.usecase.GetAllProfilesImpl
import com.thezayin.useraddress.domain.usecase.GetAreaList
import com.thezayin.useraddress.domain.usecase.GetAreaListImpl
import com.thezayin.useraddress.domain.usecase.GetCityList
import com.thezayin.useraddress.domain.usecase.GetCityListImpl
import com.thezayin.useraddress.domain.usecase.GetProfileDataById
import com.thezayin.useraddress.domain.usecase.GetProfileDataByIdImpl
import com.thezayin.useraddress.domain.usecase.UpdateProfileById
import com.thezayin.useraddress.domain.usecase.UpdateProfileByIdImpl
import com.thezayin.useraddress.presentation.ProfileViewModel
import com.thezayin.userbuy.data.PlaceOrderRepositoryImpl
import com.thezayin.userbuy.domain.repository.PlaceOrderRepository
import com.thezayin.userbuy.domain.usecase.PlaceOrder
import com.thezayin.userbuy.domain.usecase.PlaceOrderImpl
import com.thezayin.userbuy.presentation.OrderViewModel
import com.thezayin.usercart.data.repository.CartProRepositoryImpl
import com.thezayin.usercart.domain.repository.CartProRepository
import com.thezayin.usercart.domain.usecase.AddToCart
import com.thezayin.usercart.domain.usecase.AddToCartImpl
import com.thezayin.usercart.domain.usecase.DeleteAllCart
import com.thezayin.usercart.domain.usecase.DeleteAllCartImpl
import com.thezayin.usercart.domain.usecase.DeleteFromCart
import com.thezayin.usercart.domain.usecase.DeleteFromCartImpl
import com.thezayin.usercart.domain.usecase.GetCartProducts
import com.thezayin.usercart.domain.usecase.GetCartProductsImpl
import com.thezayin.usercart.domain.usecase.UpdateQuantity
import com.thezayin.usercart.domain.usecase.UpdateQuantityImpl
import com.thezayin.usercart.presentation.CartViewModel
import com.thezayin.userhome.data.GetProductsRepositoryImpl
import com.thezayin.userhome.domain.repository.GetProductsRepository
import com.thezayin.userhome.domain.usecase.GetProWithImg
import com.thezayin.userhome.domain.usecase.GetProWithImgImpl
import com.thezayin.userhome.domain.usecase.GetProducts
import com.thezayin.userhome.domain.usecase.GetProductsImpl
import com.thezayin.userhome.presentation.HomeViewModel
import com.thezayin.userorderhistory.data.MyOrderRepositoryImpl
import com.thezayin.userorderhistory.domain.repository.MyOrdersRepository
import com.thezayin.userorderhistory.domain.usecase.GetMyOrders
import com.thezayin.userorderhistory.domain.usecase.GetMyOrdersImpl
import com.thezayin.userorderhistory.presentation.OrderHistoryViewModel
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dbModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { provideCartDatabase(androidContext()) }
    single { provideCartDao(get()) }
    single { provideProfileDao(get()) }
}

val userCartModule = module {
    factoryOf(::CartProRepositoryImpl) bind CartProRepository::class
    factoryOf(::GetCartProductsImpl) bind GetCartProducts::class
    factoryOf(::DeleteAllCartImpl) bind DeleteAllCart::class
    factoryOf(::AddToCartImpl) bind AddToCart::class
    factoryOf(::DeleteFromCartImpl) bind DeleteFromCart::class
    factoryOf(::UpdateQuantityImpl) bind UpdateQuantity::class
    viewModelOf(::CartViewModel)
}

val userProfile = module {
    singleOf(::ProfileRepositoryImpl) bind ProfileRepository::class
    singleOf(::AddProfileImpl) bind AddProfile::class
    singleOf(::UpdateProfileByIdImpl) bind UpdateProfileById::class
    singleOf(::DeleteProfileByIdImpl) bind DeleteProfileById::class
    singleOf(::DeleteAllProfilesImpl) bind DeleteAllProfiles::class
    singleOf(::GetAllProfilesImpl) bind GetAllProfiles::class
    singleOf(::GetProfileDataByIdImpl) bind GetProfileDataById::class
    singleOf(::AreaRepositoryImpl) bind AreaRepository::class
    singleOf(::GetAreaListImpl) bind GetAreaList::class
    singleOf(::GetCityListImpl) bind GetCityList::class
    viewModelOf(::ProfileViewModel)
}

val buyModule = module {
    viewModelOf(::OrderViewModel)
    factoryOf(::PlaceOrderRepositoryImpl) bind PlaceOrderRepository::class
    factoryOf(::PlaceOrderImpl) bind PlaceOrder::class
}

val historyModule = module {
    viewModelOf(::OrderHistoryViewModel)
    factoryOf(::MyOrderRepositoryImpl) bind MyOrdersRepository::class
    factoryOf(::GetMyOrdersImpl) bind GetMyOrders::class
}

val userHomeModule = module {
    factoryOf(::GetProductsRepositoryImpl) bind GetProductsRepository::class
    factoryOf(::GetProductsImpl) bind GetProducts::class
    factoryOf(::GetProWithImgImpl) bind GetProWithImg::class
    viewModelOf(::HomeViewModel)
}

val appModule = module {
    single { Json { ignoreUnknownKeys = true } }
    singleOf(::RemoteConfig)
}

