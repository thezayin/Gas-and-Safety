package com.thezayin.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.domain.model.OrderModel
import com.thezayin.domain.repository.HistoryRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Implementation of the OrderRepository interface that interacts with Firebase Firestore
 * to fetch user orders in real-time. This repository provides a Flow of Resource objects
 * to represent the various states of data (Loading, Success, or Error).
 */
class HistoryRepositoryImpl(private val firestore: FirebaseFirestore) : HistoryRepository {

    /**
     * Fetches a list of orders for a specific user from Firestore in real-time.
     *
     * @param userId The unique ID of the user whose orders are to be retrieved.
     * @return A Flow emitting Resource<List<OrderModel>>, representing the state of the data.
     *         The Resource can be Loading, Success (with the list of orders), or Error (in case of failure).
     */
    override fun fetchUserOrders(userId: String): Flow<Resource<List<OrderModel>>> =
        callbackFlow {
            try {
                // Emit a Loading state while waiting for Firestore to respond
                trySend(Resource.Loading)

                // Query Firestore for orders where the userID matches the provided userId
                val orderListener = firestore.collection("user_order")
                    .whereEqualTo("userID", userId)
                    .addSnapshotListener { snapShot, error ->

                        // Convert Firestore snapshot to a list of OrderModel if data is received
                        val resource = if (snapShot != null) {
                            val orderList = snapShot.toObjects(OrderModel::class.java)
                            Resource.Success(orderList)
                        } else {
                            // Emit an error state if there's a problem with the Firestore request
                            Resource.Error(error?.message ?: "Unknown error")
                        }

                        // Send the result (Success or Error) through the Flow channel
                        trySend(resource).isSuccess
                    }

                // Ensure the Firestore listener is removed when the Flow is closed or cancelled
                awaitClose {
                    orderListener.remove()
                    channel.close()
                }
            } catch (e: Exception) {
                // Handle any exceptions and emit an error state with the exception message
                trySend(Resource.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
}