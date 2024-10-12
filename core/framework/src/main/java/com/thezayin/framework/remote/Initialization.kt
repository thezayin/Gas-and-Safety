package com.thezayin.framework.remote

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object Initialization {
    fun initializeOrderCounter(fireStore: FirebaseFirestore) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val counterRef = fireStore.collection("counters").document("orderCounter")
                val snapshot = counterRef.get().await()
                if (!snapshot.exists()) {
                    // Initialize with starting order ID
                    counterRef.set(mapOf("lastOrderId" to 121000)).await()
                    println("Order counter initialized.")
                } else {
                    println("Order counter already exists.")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle initialization errors if necessary
            }
        }
    }
}