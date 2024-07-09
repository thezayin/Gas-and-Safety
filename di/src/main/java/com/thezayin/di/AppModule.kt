package com.thezayin.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.adminaddproducts.data.AddProductRepositoryImpl
import com.thezayin.adminaddproducts.domain.repository.AddProductRepository
import com.thezayin.adminaddproducts.domain.usecase.AddProductUseCase
import com.thezayin.adminaddproducts.domain.usecase.AddProductUseCaseImpl
import com.thezayin.adminaddproducts.domain.usecase.UploadImage
import com.thezayin.adminaddproducts.domain.usecase.UploadImageImpl
import com.thezayin.adminaddproducts.presentation.AddProductViewModel
import com.thezayin.adminhome.data.AdminOptionMenuRepositoryImpl
import com.thezayin.adminhome.domain.repository.AdminOptionMenuRepository
import com.thezayin.adminhome.domain.usecase.AdminOptionMenuUseCase
import com.thezayin.adminhome.domain.usecase.AdminOptionMenuUseCaseImpl
import com.thezayin.adminhome.presentation.AdminHomeViewModel
import com.thezayin.adminorders.data.FetchOrdersRepositoryImpl
import com.thezayin.adminorders.data.OrderStatusRepositoryImpl
import com.thezayin.adminorders.domain.repository.FetchOrdersRepository
import com.thezayin.adminorders.domain.repository.OrderStatusRepository
import com.thezayin.adminorders.domain.usecase.FetchOrders
import com.thezayin.adminorders.domain.usecase.FetchOrdersImpl
import com.thezayin.adminorders.domain.usecase.GetStatusList
import com.thezayin.adminorders.domain.usecase.GetStatusListImpl
import com.thezayin.adminorders.domain.usecase.UpdateOrderStatus
import com.thezayin.adminorders.domain.usecase.UpdateOrderStatusImpl
import com.thezayin.adminorders.presentation.FetchOrdersViewModel
import com.thezayin.adminproductdetails.data.ProDetailsRepositoryImpl
import com.thezayin.adminproductdetails.domain.repository.ProDetailsRepository
import com.thezayin.adminproductdetails.domain.usecase.DeleteAdminProduct
import com.thezayin.adminproductdetails.domain.usecase.DeleteAdminProductImpl
import com.thezayin.adminproductdetails.domain.usecase.UpdateAdminProduct
import com.thezayin.adminproductdetails.domain.usecase.UpdateAdminProductIml
import com.thezayin.adminproductdetails.domain.usecase.UpdateImage
import com.thezayin.adminproductdetails.domain.usecase.UpdateImageImpl
import com.thezayin.adminproductdetails.presentation.AdminProDetailsViewModel
import com.thezayin.adminproducts.data.AdminProductRepositoryImpl
import com.thezayin.adminproducts.data.GetProductImagesRepositoryImpl
import com.thezayin.adminproducts.domain.repository.AdminProductRepository
import com.thezayin.adminproducts.domain.repository.GetProductImagesRepository
import com.thezayin.adminproducts.domain.usecase.GetAdminProduct
import com.thezayin.adminproducts.domain.usecase.GetAdminProductImpl
import com.thezayin.adminproducts.domain.usecase.GetProductImages
import com.thezayin.adminproducts.domain.usecase.GetProductImagesImpl
import com.thezayin.adminproducts.presentation.AdminProductViewModel
import com.thezayin.framework.remote.RemoteConfig
import com.thezayin.useraddress.data.di.provideProfileDatabase
import com.thezayin.useraddress.data.di.provideProfileDao
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
import com.thezayin.usercart.data.di.provideCartDao
import com.thezayin.usercart.data.di.provideCartDatabase
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

/**
 * User Modules
 */
val userCartModule = module {
    single { provideCartDatabase(androidContext()) }
    single { provideCartDao(get()) }
    factoryOf(::CartProRepositoryImpl) bind CartProRepository::class
    factoryOf(::GetCartProductsImpl) bind GetCartProducts::class
    factoryOf(::DeleteAllCartImpl) bind DeleteAllCart::class
    factoryOf(::AddToCartImpl) bind AddToCart::class
    factoryOf(::DeleteFromCartImpl) bind DeleteFromCart::class
    factoryOf(::UpdateQuantityImpl) bind UpdateQuantity::class
    viewModelOf(::CartViewModel)
}

val userProfile = module {
    single { provideProfileDatabase(androidContext()) }
    single { provideProfileDao(get()) }
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

/**
 * Admin Modules
 */
val appModule = module {
    single { Json { ignoreUnknownKeys = true } }
    singleOf(::RemoteConfig)
}

val getUserOrdersModule = module {
    viewModelOf(::FetchOrdersViewModel)
    factoryOf(::FetchOrdersRepositoryImpl) bind FetchOrdersRepository::class
    factoryOf(::OrderStatusRepositoryImpl) bind OrderStatusRepository::class
    factoryOf(::GetStatusListImpl) bind GetStatusList::class
    factoryOf(::UpdateOrderStatusImpl) bind UpdateOrderStatus::class
    factoryOf(::FetchOrdersImpl) bind FetchOrders::class
}

val adminProUpdateModule = module {
    viewModelOf(::AdminProDetailsViewModel)
    factoryOf(::ProDetailsRepositoryImpl) bind ProDetailsRepository::class
    factoryOf(::DeleteAdminProductImpl) bind DeleteAdminProduct::class
    factoryOf(::UpdateAdminProductIml) bind UpdateAdminProduct::class
    factoryOf(::UpdateImageImpl) bind UpdateImage::class
}

val adminProductModule = module {
    viewModelOf(::AdminProductViewModel)
    factoryOf(::AdminProductRepositoryImpl) bind AdminProductRepository::class
    factoryOf(::GetProductImagesImpl) bind GetProductImages::class
    factoryOf(::GetProductImagesRepositoryImpl) bind GetProductImagesRepository::class
    factoryOf(::GetAdminProductImpl) bind GetAdminProduct::class
}


val adminHomeModule = module {
    viewModelOf(::AdminHomeViewModel)
    factoryOf(::AdminOptionMenuRepositoryImpl) bind AdminOptionMenuRepository::class
    factoryOf(::AdminOptionMenuUseCaseImpl) bind AdminOptionMenuUseCase::class
}

val adminAddProductModule = module {
    viewModelOf(::AddProductViewModel)
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance() }
    factoryOf(::AddProductRepositoryImpl) bind AddProductRepository::class
    factoryOf(::AddProductUseCaseImpl) bind AddProductUseCase::class
    factoryOf(::UploadImageImpl) bind UploadImage::class
}

