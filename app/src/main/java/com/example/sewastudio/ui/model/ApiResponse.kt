package com.example.sewastudio.ui.model

import com.google.gson.annotations.SerializedName


data class ApiResponse<T>(
    @SerializedName("data")
    val data: T? = null
)