package com.thezayin.lpg.common.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.messaging.FirebaseMessaging
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.lpg.NavGraphs
import com.thezayin.lpg.common.theme.LPGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                println("Token: $token")
            }
            val token = task.result
        }
        enableEdgeToEdge()
        setContent {
            LPGTheme { DestinationsNavHost(navGraph = NavGraphs.root) }
        }
    }
}