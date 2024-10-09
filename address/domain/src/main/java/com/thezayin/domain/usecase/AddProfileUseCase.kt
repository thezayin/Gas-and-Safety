package com.thezayin.domain.usecase

import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case for adding a new user profile.
 *
 * This interface defines the contract for adding a user profile to the data source.
 * Implementations of this use case should handle the necessary business logic and data interactions.
 */

/**
 * Executes the use case to add a new user profile.
 *
 * The parameters required to create a new profile, encapsulated in [AddProfileParams].
 * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
 */
interface AddProfileUseCase : suspend (AddProfileParams) -> Flow<Resource<Boolean>>


/**
 * Data class representing the parameters required to add a new user profile.
 *
 * @property name The full name of the user.
 * @property phoneNumber The user's contact phone number.
 * @property address The primary address of the user.
 * @property area The area or neighborhood of the user's address.
 * @property city The city where the user resides.
 * @property email The user's email address. Optional.
 */
data class AddProfileParams(
    val name: String,
    val phoneNumber: String,
    val address: String,
    val area: String,
    val city: String,
    val email: String? = null
)

/**
 * Implementation of the [AddProfileUseCase] interface.
 *
 * This class handles the addition of a new user profile by interacting with the [ProfileRepository].
 *
 * @property profileRepository The repository responsible for profile-related data operations.
 */
class AddProfileUseCaseImpl(
    private val profileRepository: ProfileRepository
) : AddProfileUseCase {

    /**
     * Invoke the use case to add a new user profile.
     *
     * @param params The parameters required to create a new profile, encapsulated in [AddProfileParams].
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    override suspend fun invoke(params: AddProfileParams): Flow<Resource<Boolean>> =
        profileRepository.addProfile(
            name = params.name,
            phoneNumber = params.phoneNumber,
            address = params.address,
            area = params.area,
            city = params.city,
            email = params.email
        )
}