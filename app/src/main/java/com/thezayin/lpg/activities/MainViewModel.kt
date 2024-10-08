package com.thezayin.lpg.activities

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.thezayin.analytics.analytics.Analytics

@SuppressLint("NewApi")
class MainViewModel(
    val analyticsHelper: Analytics,
    val remoteConfig: com.thezayin.framework.remote.RemoteConfig
) : ViewModel()