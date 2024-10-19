package com.thezayin.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.domain.model.HomeProdModel
import com.thezayin.domain.repository.ProductRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

/**
 * Implementation of the ProductRepository interface for managing product-related data operations.
 *
 * This class interacts with Firestore and Firebase Storage to fetch product data and associated images.
 *
 * @property firestore The Firestore instance for database operations.
 * @property storage The Firebase Storage instance for image retrieval.
 */
class ProductRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : ProductRepository {

    /**
     * Retrieves a list of products from Firestore.
     *
     * This method listens for changes in the "products" collection and emits a Flow
     * of Resource containing the list of HomeProdModel or an error state.
     *
     * @return A Flow emitting Resource<List<HomeProdModel>> containing the list of products or an error state.
     */
    override fun fetchProducts(): Flow<Resource<List<HomeProdModel>>> = callbackFlow {
        trySend(Resource.Loading)

        // Fetch products ordered by the `date` field in ascending order (oldest first)
        val snapshotListener = firestore.collection("products")
            .orderBy("date", Query.Direction.DESCENDING) // Change to ascending order
            .addSnapshotListener { snapshot, error ->
                val resource = if (snapshot != null) {
                    val productList = snapshot.toObjects(HomeProdModel::class.java)
                    Resource.Success(productList)
                } else {
                    Resource.Error(error?.message ?: "Unknown error occurred")
                }
                trySend(resource).isSuccess
            }

        awaitClose {
            snapshotListener.remove()
            channel.close()
        }
    }


    /**
     * Retrieves products along with their associated images from Firebase Storage.
     *
     * This method fetches image URLs for the given list of products and emits a Flow
     * of Resource containing the list of products with images or an error state.
     *
     * @param products A list of HomeProdModel representing the products for which images are to be fetched.
     * @return A Flow emitting Resource<List<HomeProdModel>> containing the list of products with images or an error state.
     */
    override fun fetchProductsWithImages(products: List<HomeProdModel>): Flow<Resource<List<HomeProdModel>>> =
        callbackFlow {
            val enrichedProductList = mutableListOf<HomeProdModel>()

            trySend(Resource.Loading) // Emit loading state

            // Iterate through each product to fetch its image
            for (product in products) {
                product.imageUrl?.let { imagePath ->
                    storage.reference.child(imagePath).downloadUrl.addOnSuccessListener { uri ->
                        enrichedProductList.add(
                            HomeProdModel(
                                id = product.id,
                                name = product.name,
                                description = product.description,
                                price = product.price,
                                imageUrl = product.imageUrl,
                                imageUri = uri,
                                date = product.date
                            )
                        )
                    }.addOnFailureListener { error ->
                        // Emit error state if fetching image fails
                        trySend(Resource.Error(error.localizedMessage ?: "Error fetching image"))
                    }.await()
                }
            }
            // Emit success state after processing all products
            trySend(Resource.Success(enrichedProductList))
            awaitClose() // Await closure
        }
}