package com.thezayin.framework.remote

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.thezayin.assets.R

object ForceUpdateManager {

    private const val TAG = "ForceUpdateManager"
    private const val FORCE_UPDATE_PARAM = "force_update_version"

    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun initialize(context: Context) {
        remoteConfig = FirebaseRemoteConfig.getInstance()

        // Set Remote Config settings
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600) // 1 hour; adjust as needed
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)

        // Set default values
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    fun checkForceUpdate(activity: Activity) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Remote Config fetched and activated.")
                    val latestVersionCode = remoteConfig.getLong(FORCE_UPDATE_PARAM).toInt()
                    val currentVersionCode = getAppVersionCode(activity)

                    Log.d(TAG, "Latest Version Code: $latestVersionCode")
                    Log.d(TAG, "Current Version Code: $currentVersionCode")

                    if (currentVersionCode < latestVersionCode) {
                        showForceUpdateDialog(activity)
                    } else {
                        Log.d(TAG, "App is up to date.")
                    }
                } else {
                    Log.e(TAG, "Failed to fetch Remote Config.")
                    // Optionally, you can decide how to handle fetch failures
                }
            }
    }

    private fun getAppVersionCode(context: Context): Int {
        return try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Failed to get app version code.", e)
            0
        }
    }

    private fun showForceUpdateDialog(activity: Activity) {
        AlertDialog.Builder(activity)
            .setTitle("Update Available")
            .setMessage("A newer version of this app is available. Please update to continue.")
            .setCancelable(false)
            .setPositiveButton("Update") { _, _ ->
                // Redirect to Play Store or your app's update URL
                redirectStore(activity)
            }
            .show()
    }

    private fun redirectStore(activity: Activity) {
        val appPackageName = activity.packageName
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (e: android.content.ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }
}