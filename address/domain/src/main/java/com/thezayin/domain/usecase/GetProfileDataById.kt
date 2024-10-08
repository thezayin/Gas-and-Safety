package com.thezayin.domain.usecase

import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Use case for retrieving a user profile by its unique identifier.
 *
 * This interface defines the contract for fetching a single user profile from the data source
 * based on the provided profile ID. Implementations of this use case should handle the necessary
 * business logic and data interactions.
 */
interface GetProfileByIdUseCase : suspend (GetProfileByIdParams) -> Flow<Response<ProfileModel>>

/**
 * Data class representing the parameters required to fetch a user profile.
 *
 * @property id The unique identifier of the profile to retrieve.
 */
data class GetProfileByIdParams(
    val id: Int
)

/**
 * Implementation of the [GetProfileByIdUseCase] interface.
 *
 * This class handles the retrieval of a specific user profile by interacting with the [ProfileRepository].
 *
 * @property profileRepository The repository responsible for profile-related data operations.
 */
class GetProfileByIdUseCaseImpl(
    private val profileRepository: ProfileRepository
) : GetProfileByIdUseCase {

    /**
     * Invokes the use case to fetch a specific user profile.
     *
     * @param params The parameters required to identify the profile, encapsulated in [GetProfileByIdParams].
     * @return A [Flow] emitting a [Response] containing the [ProfileModel] on success
     *         or an error message on failure.
     */
    override suspend fun invoke(params: GetProfileByIdParams): Flow<Response<ProfileModel>> = flow {
        emit(Response.Loading) // Indicate that the operation has started
        try {
            // Fetch the profile from the repository using the provided ID
            val responseFlow = profileRepository.getProfileById(params.id)
            emitAll(responseFlow) // Emit the response from the repository
        } catch (e: Exception) {
            // Emit an error response if fetching the profile fails
            emit(Response.Error("Failed to retrieve profile with ID ${params.id}: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO) // Perform the operation on the IO dispatcher
}