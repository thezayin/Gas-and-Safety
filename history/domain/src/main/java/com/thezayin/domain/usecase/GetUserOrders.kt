package com.thezayin.domain.usecase

import com.thezayin.domain.model.OrderModel
import com.thezayin.domain.repository.HistoryRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Interface representing a use case to retrieve orders for a specific user.
 * It takes a userId as input and returns a Flow that emits a Resource containing a list of OrderModel objects.
 */
interface GetUserOrders : suspend (String) -> Flow<Resource<List<OrderModel>>>

/**
 * Implementation of the GetUserOrders use case.
 * This class interacts with the OrderRepository to fetch user orders and handle the business logic.
 *
 * @param repository The OrderRepository that provides the method to fetch orders from the data source.
 */
class GetUserOrdersImpl(private val repository: HistoryRepository) : GetUserOrders {

    /**
     * Invokes the use case to fetch orders for the provided userId.
     * This method is called asynchronously and returns a Flow that emits Resource objects, representing the order data or any errors.
     *
     * @param userId The unique ID of the user whose orders need to be fetched.
     * @return A Flow emitting Resource<List<OrderModel>>, representing the state of the orders (Loading, Success, or Error).
     */
    override suspend fun invoke(userId: String): Flow<Resource<List<OrderModel>>> =
        repository.fetchUserOrders(userId)
}
