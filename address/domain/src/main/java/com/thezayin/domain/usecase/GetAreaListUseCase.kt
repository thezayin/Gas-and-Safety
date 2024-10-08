package com.thezayin.domain.usecase

import com.thezayin.domain.repository.AreaRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Use case for retrieving a list of areas within a specified city.
 *
 * This interface defines the contract for fetching all areas associated with a given city from the data source.
 * Implementations of this use case should handle the necessary business logic and data interactions.
 */
interface GetAreaListUseCase : suspend (GetAreaListParams) -> Flow<Resource<List<String>>>

/**
 * Data class representing the parameters required to fetch the area list.
 *
 * @property city The name of the city for which to retrieve the list of areas.
 */
data class GetAreaListParams(
    val city: String
)

/**
 * Implementation of the [GetAreaListUseCase] interface.
 *
 * This class handles the retrieval of area lists by interacting with the [AreaRepository].
 *
 * @property areaRepository The repository responsible for area-related data operations.
 */
class GetAreaListUseCaseImpl(
    private val areaRepository: AreaRepository
) : GetAreaListUseCase {

    /**
     * Invokes the use case to fetch the list of areas for a specified city.
     *
     * @param params The parameters required to identify the city, encapsulated in [GetAreaListParams].
     * @return A [Flow] emitting a [Resource] containing a list of area names on success
     *         or an error message on failure.
     */
    override suspend fun invoke(params: GetAreaListParams): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading) // Indicate that the operation has started
        try {
            // Fetch the area list from the repository based on the provided city
            val responseFlow = areaRepository.getAreaList(params.city)
            emitAll(responseFlow) // Emit the response from the repository
        } catch (e: Exception) {
            // Emit an error response if fetching areas fails
            emit(Resource.Error("Failed to retrieve areas for city '${params.city}': ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO) // Perform the operation on the IO dispatcher
}