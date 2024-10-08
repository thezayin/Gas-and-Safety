package com.thezayin.lpg.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
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
        enableEdgeToEdge()
        setContent {
            LPGTheme {
                val navController = rememberNavController()
                NavHost(navController = navController)
            }
        }
    }
}