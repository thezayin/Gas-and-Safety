package com.thezayin.domain.usecase

import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Use case for deleting all user profiles.
 *
 * This interface defines the contract for removing all user profiles from the data source.
 * Implementations of this use case should handle the necessary business logic and data interactions.
 */
interface DeleteAllProfilesUseCase : suspend () -> Flow<Response<Boolean>>

/**
 * Implementation of the [DeleteAllProfilesUseCase] interface.
 *
 * This class handles the deletion of all user profiles by interacting with the [ProfileRepository].
 *
 * @property profileRepository The repository responsible for profile-related data operations.
 */
class DeleteAllProfilesUseCaseImpl(
    private val profileRepository: ProfileRepository
) : DeleteAllProfilesUseCase {

    /**
     * Invokes the use case to delete all user profiles.
     *
     * @return A [Flow] emitting a [Response] indicating the success or failure of the operation.
     */
    override suspend fun invoke(): Flow<Response<Boolean>> = profileRepository.deleteAllProfiles()
}