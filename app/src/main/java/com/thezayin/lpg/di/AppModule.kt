package com.thezayin.lpg.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.data.GetProductsRepositoryImpl
import com.thezayin.data.MyOrderRepositoryImpl
import com.thezayin.data.PlaceOrderRepositoryImpl
import com.thezayin.data.di.provideCartDao
import com.thezayin.data.di.provideDatabase
import com.thezayin.data.di.provideProfileDao
import com.thezayin.data.repository.AreaRepositoryImpl
import com.thezayin.data.repository.CartProRepositoryImpl
import com.thezayin.data.repository.ProfileRepositoryImpl
import com.thezayin.domain.CartProRepository
import com.thezayin.domain.MyOrdersRepository
import com.thezayin.framework.remote.RemoteConfig
import com.thezayin.lpg.common.activities.MainViewModel
import com.thezayin.lpg.presentation.admin.addProducts.data.AddProductRepositoryImpl
import com.thezayin.lpg.presentation.admin.addProducts.domain.repository.AddProductRepository
import com.thezayin.lpg.presentation.admin.addProducts.domain.usecase.AddProductUseCase
import com.thezayin.lpg.presentation.admin.addProducts.domain.usecase.AddProductUseCaseImpl
import com.thezayin.lpg.presentation.admin.addProducts.domain.usecase.UploadImage
import com.thezayin.lpg.presentation.admin.addProducts.domain.usecase.UploadImageImpl
import com.thezayin.lpg.presentation.admin.addProducts.presentation.AddProductViewModel
import com.thezayin.lpg.presentation.admin.adminHome.data.AdminOptionMenuRepositoryImpl
import com.thezayin.lpg.presentation.admin.adminHome.domain.repository.AdminOptionMenuRepository
import com.thezayin.lpg.presentation.admin.adminHome.domain.usecase.AdminOptionMenuUseCase
import com.thezayin.lpg.presentation.admin.adminHome.domain.usecase.AdminOptionMenuUseCaseImpl
import com.thezayin.lpg.presentation.admin.adminHome.presentation.AdminHomeViewModel
import com.thezayin.lpg.presentation.admin.fetchOrders.data.FetchOrdersRepositoryImpl
import com.thezayin.lpg.presentation.admin.fetchOrders.data.OrderStatusRepositoryImpl
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.repository.FetchOrdersRepository
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.repository.OrderStatusRepository
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.FetchOrders
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.FetchOrdersImpl
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.GetStatusList
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.GetStatusListImpl
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.UpdateOrderStatus
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.UpdateOrderStatusImpl
import com.thezayin.lpg.presentation.admin.fetchOrders.presentation.FetchOrdersViewModel
import com.thezayin.lpg.presentation.admin.product_details.data.ProDetailsRepositoryImpl
import com.thezayin.lpg.presentation.admin.product_details.domain.repository.ProDetailsRepository
import com.thezayin.lpg.presentation.admin.product_details.domain.usecase.DeleteAdminProduct
import com.thezayin.lpg.presentation.admin.product_details.domain.usecase.DeleteAdminProductImpl
import com.thezayin.lpg.presentation.admin.product_details.domain.usecase.UpdateAdminProduct
import com.thezayin.lpg.presentation.admin.product_details.domain.usecase.UpdateAdminProductIml
import com.thezayin.lpg.presentation.admin.product_details.domain.usecase.UpdateImage
import com.thezayin.lpg.presentation.admin.product_details.domain.usecase.UpdateImageImpl
import com.thezayin.lpg.presentation.admin.product_details.presentation.AdminProDetailsViewModel
import com.thezayin.lpg.presentation.admin.products.data.AdminProductRepositoryImpl
import com.thezayin.lpg.presentation.admin.products.data.GetProductImagesRepositoryImpl
import com.thezayin.lpg.presentation.admin.products.domain.repository.AdminProductRepository
import com.thezayin.lpg.presentation.admin.products.domain.repository.GetProductImagesRepository
import com.thezayin.lpg.presentation.admin.products.domain.usecase.GetAdminProduct
import com.thezayin.lpg.presentation.admin.products.domain.usecase.GetAdminProductImpl
import com.thezayin.lpg.presentation.admin.products.domain.usecase.GetProductImages
import com.thezayin.lpg.presentation.admin.products.domain.usecase.GetProductImagesImpl
import com.thezayin.lpg.presentation.admin.products.presentation.AdminProductViewModel
import com.thezayin.lpg.presentation.users.cart.CartViewModel
import com.thezayin.lpg.presentation.users.history.OrderHistoryViewModel
import com.thezayin.lpg.presentation.users.home.HomeViewModel
import com.thezayin.lpg.presentation.users.login.data.remote.VerifyNumberRepositoryImpl
import com.thezayin.lpg.presentation.users.login.domain.repository.VerifyNumberRepository
import com.thezayin.lpg.presentation.users.login.domain.usecase.CreateUser
import com.thezayin.lpg.presentation.users.login.domain.usecase.CreateUserImpl
import com.thezayin.lpg.presentation.users.login.domain.usecase.VerifyOTP
import com.thezayin.lpg.presentation.users.login.domain.usecase.VerifyOTPImpl
import com.thezayin.lpg.presentation.users.login.presentation.LoginViewModel
import com.thezayin.lpg.presentation.users.placeOrder.OrderViewModel
import com.thezayin.lpg.presentation.users.profile.ProfileViewModel
import com.thezayin.usecase.AddProfileImpl
import com.thezayin.usecase.AddToCart
import com.thezayin.usecase.AddToCartImpl
import com.thezayin.usecase.DeleteAllCart
import com.thezayin.usecase.DeleteAllCartImpl
import com.thezayin.usecase.DeleteAllProfilesImpl
import com.thezayin.usecase.DeleteFromCart
import com.thezayin.usecase.DeleteFromCartImpl
import com.thezayin.usecase.DeleteProfileByIdImpl
import com.thezayin.usecase.GetAllProfilesImpl
import com.thezayin.usecase.GetAreaListImpl
import com.thezayin.usecase.GetCartProducts
import com.thezayin.usecase.GetCartProductsImpl
import com.thezayin.usecase.GetCityListImpl
import com.thezayin.usecase.GetMyOrdersImpl
import com.thezayin.usecase.GetProWithImgImpl
import com.thezayin.usecase.GetProductsImpl
import com.thezayin.usecase.GetProfileDataByIdImpl
import com.thezayin.usecase.PlaceOrderImpl
import com.thezayin.usecase.UpdateProfileByIdImpl
import com.thezayin.usecase.UpdateQuantity
import com.thezayin.usecase.UpdateQuantityImpl
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { Json { ignoreUnknownKeys = true } }
    singleOf(::RemoteConfig)
    viewModelOf(::MainViewModel)
}

val adminHomeModule = module {
    factoryOf(::AdminOptionMenuRepositoryImpl) bind AdminOptionMenuRepository::class
    factoryOf(::AdminOptionMenuUseCaseImpl) bind AdminOptionMenuUseCase::class
    viewModelOf(::AdminHomeViewModel)
}

val adminAddProductModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance() }
    viewModelOf(::AddProductViewModel)
    factoryOf(::AddProductRepositoryImpl) bind AddProductRepository::class
    factoryOf(::AddProductUseCaseImpl) bind AddProductUseCase::class
    factoryOf(::UploadImageImpl) bind UploadImage::class
}

val adminProductModule = module {
    viewModelOf(::AdminProductViewModel)
    factoryOf(::AdminProductRepositoryImpl) bind AdminProductRepository::class
    factoryOf(::GetProductImagesImpl) bind GetProductImages::class
    factoryOf(::GetProductImagesRepositoryImpl) bind GetProductImagesRepository::class
    factoryOf(::GetAdminProductImpl) bind GetAdminProduct::class
}

val adminProUpdateModule = module {
    viewModelOf(::AdminProDetailsViewModel)
    factoryOf(::ProDetailsRepositoryImpl) bind ProDetailsRepository::class
    factoryOf(::DeleteAdminProductImpl) bind DeleteAdminProduct::class
    factoryOf(::UpdateAdminProductIml) bind UpdateAdminProduct::class
    factoryOf(::UpdateImageImpl) bind UpdateImage::class
}

val getUserOrdersModule = module {
    viewModelOf(::FetchOrdersViewModel)
    factoryOf(::FetchOrdersRepositoryImpl) bind FetchOrdersRepository::class
    factoryOf(::OrderStatusRepositoryImpl) bind OrderStatusRepository::class
    factoryOf(::GetStatusListImpl) bind GetStatusList::class
    factoryOf(::UpdateOrderStatusImpl) bind UpdateOrderStatus::class
    factoryOf(::FetchOrdersImpl) bind FetchOrders::class
}

/**
 * User Modules
 */
val userHomeModule = module {
    viewModelOf(::HomeViewModel)
    factoryOf(::GetProductsRepositoryImpl) bind com.thezayin.domain.GetProductsRepository::class
    factoryOf(::GetProductsImpl) bind com.thezayin.usecase.GetProducts::class
    factoryOf(::GetProWithImgImpl) bind com.thezayin.usecase.GetProWithImg::class
}

val userCartModule = module {
    single { provideDatabase(androidContext()) }
    single { provideCartDao(get()) }
    viewModelOf(::CartViewModel)
    factoryOf(::CartProRepositoryImpl) bind CartProRepository::class
    factoryOf(::GetCartProductsImpl) bind GetCartProducts::class
    factoryOf(::DeleteAllCartImpl) bind DeleteAllCart::class
    factoryOf(::AddToCartImpl) bind AddToCart::class
    factoryOf(::DeleteFromCartImpl) bind DeleteFromCart::class
    factoryOf(::UpdateQuantityImpl) bind UpdateQuantity::class
}

val loginModule = module {
    viewModelOf(::LoginViewModel)
    factoryOf(::VerifyNumberRepositoryImpl) bind VerifyNumberRepository::class
    factoryOf(::CreateUserImpl) bind CreateUser::class
    factoryOf(::VerifyOTPImpl) bind VerifyOTP::class
}

val placeOrderViewModelModule = module {
    viewModelOf(::OrderViewModel)
    factoryOf(::PlaceOrderRepositoryImpl) bind com.thezayin.domain.PlaceOrderRepository::class
    factoryOf(::PlaceOrderImpl) bind com.thezayin.usecase.PlaceOrder::class
}

val historyModule = module {
    viewModelOf(::OrderHistoryViewModel)
    factoryOf(::MyOrderRepositoryImpl) bind MyOrdersRepository::class
    factoryOf(::GetMyOrdersImpl) bind com.thezayin.usecase.GetMyOrders::class
}

val userProfile = module {
    single { provideProfileDao(get()) }
    singleOf(::ProfileRepositoryImpl) bind com.thezayin.domain.ProfileRepository::class

    singleOf(::AddProfileImpl) bind com.thezayin.usecase.AddProfile::class
    singleOf(::UpdateProfileByIdImpl) bind com.thezayin.usecase.UpdateProfileById::class
    singleOf(::DeleteProfileByIdImpl) bind com.thezayin.usecase.DeleteProfileById::class
    singleOf(::DeleteAllProfilesImpl) bind com.thezayin.usecase.DeleteAllProfiles::class
    singleOf(::GetAllProfilesImpl) bind com.thezayin.usecase.GetAllProfiles::class
    singleOf(::GetProfileDataByIdImpl) bind com.thezayin.usecase.GetProfileDataById::class

    singleOf(::AreaRepositoryImpl) bind com.thezayin.domain.AreaRepository::class
    singleOf(::GetAreaListImpl) bind com.thezayin.usecase.GetAreaList::class
    singleOf(::GetCityListImpl) bind com.thezayin.usecase.GetCityList::class
    viewModelOf(::ProfileViewModel)
}