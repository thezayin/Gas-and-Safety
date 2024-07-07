package com.thezayin.lpg.presentation.admin.product_details.domain.model

import android.graphics.Bitmap

data class DetailsProductModel(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: String? = null,
    val imageUri: Bitmap? = null,
    val date: String? = null,
)