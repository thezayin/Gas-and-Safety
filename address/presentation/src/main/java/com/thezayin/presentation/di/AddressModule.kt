package com.thezayin.presentation.di

import com.thezayin.data.repository.AreaRepositoryImpl
import com.thezayin.data.repository.ProfileRepositoryImpl
import com.thezayin.domain.repository.AreaRepository
import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.domain.usecase.AddProfileUseCase
import com.thezayin.domain.usecase.AddProfileUseCaseImpl
import com.thezayin.domain.usecase.DeleteAllProfilesUseCase
import com.thezayin.domain.usecase.DeleteAllProfilesUseCaseImpl
import com.thezayin.domain.usecase.DeleteProfileByIdUseCase
import com.thezayin.domain.usecase.DeleteProfileByIdUseCaseImpl
import com.thezayin.domain.usecase.GetAllProfilesUseCase
import com.thezayin.domain.usecase.GetAllProfilesUseCaseImpl
import com.thezayin.domain.usecase.GetAreaListUseCase
import com.thezayin.domain.usecase.GetAreaListUseCaseImpl
import com.thezayin.domain.usecase.GetCityListUseCase
import com.thezayin.domain.usecase.GetCityListUseCaseImpl
import com.thezayin.domain.usecase.GetProfileByIdUseCase
import com.thezayin.domain.usecase.GetProfileByIdUseCaseImpl
import com.thezayin.domain.usecase.UpdateProfileUseCase
import com.thezayin.domain.usecase.UpdateProfileUseCaseImpl
import com.thezayin.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module for providing all dependencies related to profiles and areas.
 * This module includes repositories, use cases, and the ViewModel required
 * for managing profile and area-related operations.
 */
val addressModule = module {

    // Repositories
    /**
     * Provides a singleton instance of [ProfileRepositoryImpl] bound to the [ProfileRepository] interface.
     * This is responsible for handling profile-related data operations.
     */
    singleOf(::ProfileRepositoryImpl) bind ProfileRepository::class

    /**
     * Provides a singleton instance of [AreaRepositoryImpl] bound to the [AreaRepository] interface.
     * This is responsible for handling area and city-related data operations.
     */
    singleOf(::AreaRepositoryImpl) bind AreaRepository::class

    // Use Cases: Profile Operations
    /**
     * Provides a singleton instance of [AddProfileUseCaseImpl] bound to [AddProfileUseCase].
     * This is responsible for adding a new user profile.
     */
    singleOf(::AddProfileUseCaseImpl) bind AddProfileUseCase::class

    /**
     * Provides a singleton instance of [UpdateProfileUseCaseImpl] bound to [UpdateProfileUseCase].
     * This is responsible for updating an existing user profile.
     */
    singleOf(::UpdateProfileUseCaseImpl) bind UpdateProfileUseCase::class

    /**
     * Provides a singleton instance of [DeleteProfileByIdUseCaseImpl] bound to [DeleteProfileByIdUseCase].
     * This is responsible for deleting a user profile by its ID.
     */
    singleOf(::DeleteProfileByIdUseCaseImpl) bind DeleteProfileByIdUseCase::class

    /**
     * Provides a singleton instance of [DeleteAllProfilesUseCaseImpl] bound to [DeleteAllProfilesUseCase].
     * This is responsible for deleting all user profiles.
     */
    singleOf(::DeleteAllProfilesUseCaseImpl) bind DeleteAllProfilesUseCase::class

    /**
     * Provides a singleton instance of [GetAllProfilesUseCaseImpl] bound to [GetAllProfilesUseCase].
     * This is responsible for retrieving all user profiles.
     */
    singleOf(::GetAllProfilesUseCaseImpl) bind GetAllProfilesUseCase::class

    /**
     * Provides a singleton instance of [GetProfileByIdUseCaseImpl] bound to [GetProfileByIdUseCase].
     * This is responsible for retrieving a specific user profile by its ID.
     */
    singleOf(::GetProfileByIdUseCaseImpl) bind GetProfileByIdUseCase::class

    // Use Cases: Area and City Operations
    /**
     * Provides a singleton instance of [GetAreaListUseCaseImpl] bound to [GetAreaListUseCase].
     * This is responsible for retrieving the list of areas within a specified city.
     */
    singleOf(::GetAreaListUseCaseImpl) bind GetAreaListUseCase::class

    /**
     * Provides a singleton instance of [GetCityListUseCaseImpl] bound to [GetCityListUseCase].
     * This is responsible for retrieving the list of available cities.
     */
    singleOf(::GetCityListUseCaseImpl) bind GetCityListUseCase::class

    // ViewModel
    /**
     * Provides a singleton instance of [ProfileViewModel] using the defined Use Cases.
     * The ViewModel will manage profile and area operations, keeping the UI updated.
     */
    viewModelOf(::ProfileViewModel)
}
