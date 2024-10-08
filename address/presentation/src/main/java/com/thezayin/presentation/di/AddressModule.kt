package com.thezayin.presentation.di

import com.thezayin.data.repository.AreaRepositoryImpl
import com.thezayin.data.repository.ProfileRepositoryImpl
import com.thezayin.domain.repository.AreaRepository
import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.domain.usecase.AddProfile
import com.thezayin.domain.usecase.AddProfileImpl
import com.thezayin.domain.usecase.DeleteAllProfiles
import com.thezayin.domain.usecase.DeleteAllProfilesImpl
import com.thezayin.domain.usecase.DeleteProfileById
import com.thezayin.domain.usecase.DeleteProfileByIdImpl
import com.thezayin.domain.usecase.GetAllProfiles
import com.thezayin.domain.usecase.GetAllProfilesImpl
import com.thezayin.domain.usecase.GetAreaList
import com.thezayin.domain.usecase.GetAreaListImpl
import com.thezayin.domain.usecase.GetCityList
import com.thezayin.domain.usecase.GetCityListImpl
import com.thezayin.domain.usecase.GetProfileDataById
import com.thezayin.domain.usecase.GetProfileDataByIdImpl
import com.thezayin.domain.usecase.UpdateProfileById
import com.thezayin.domain.usecase.UpdateProfileByIdImpl
import com.thezayin.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val addressModule = module {
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