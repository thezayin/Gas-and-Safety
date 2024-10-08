package com.thezayin.domain.usecase

import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flowOn

/**
 * Use case for retrieving all user profiles.
 *
 * This interface defines the contract for fetching all user profiles from the data source.
 * Implementations of this use case should handle the necessary business logic and data interactions.
 */
interface GetAllProfilesUseCase : suspend () -> Flow<Response<List<ProfileModel>>>

/**
 * Implementation of the [GetAllProfilesUseCase] interface.
 *
 * This class handles the retrieval of all user profiles by interacting with the [ProfileRepository].
 *
 * @property profileRepository The repository responsible for profile-related data operations.
 */
class GetAllProfilesUseCaseImpl(
    private val profileRepository: ProfileRepository
) : GetAllProfilesUseCase {

    /**
     * Invokes the use case to fetch all user profiles.
     *
     * @return A [Flow] emitting a [Response] containing a list of [ProfileModel] on success
     *         or an error message on failure.
     */
    override suspend fun invoke(): Flow<Response<List<ProfileModel>>> = flow {
        emit(Response.Loading) // Indicate that the operation has started
        try {
            // Fetch all profiles from the repository
            val responseFlow = profileRepository.getAllProfiles()
            emitAll(responseFlow) // Emit the response from the repository
        } catch (e: Exception) {
            // Emit an error response if fetching profiles fails
            emit(Response.Error("Failed to retrieve profiles: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO) // Perform the operation on the IO dispatcher
}