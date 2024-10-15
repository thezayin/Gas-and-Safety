package com.thezayin.lpg.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.thezayin.framework.remote.ForceUpdateManager
import com.thezayin.framework.remote.Initialization
import com.thezayin.lpg.navigation.NavHost
import com.thezayin.lpg.theme.LPGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                println("Token: $token")
            }
            task.result
        }
        // Initialize Firestore
        val fireStore = FirebaseFirestore.getInstance()
        // Initialize the order counter
        Initialization.initializeOrderCounter(fireStore)

        // Initialize ForceUpdateManager
        ForceUpdateManager.initialize(this)

        // Check for force update
        ForceUpdateManager.checkForceUpdate(this)

        enableEdgeToEdge()
        setContent {
            LPGTheme {
                val navController = rememberNavController()
                NavHost(navController = navController)
            }
        }
    }
}