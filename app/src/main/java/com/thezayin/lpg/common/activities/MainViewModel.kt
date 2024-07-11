package com.thezayin.lpg.common.activities

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.thezayin.analytics.helpers.AnalyticsHelper
import com.thezayin.framework.remote.RemoteConfig

@SuppressLint("NewApi")
class MainViewModel(
    val analyticsHelper: AnalyticsHelper,
    val remoteConfig: RemoteConfig
) : ViewModel()