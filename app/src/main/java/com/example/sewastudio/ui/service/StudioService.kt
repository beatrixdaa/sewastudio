package com.example.sewastudio.ui.service


import com.example.sewastudio.ui.model.Studio
import com.example.sewastudio.ui.model.StudioResponse
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
data class StudioData(
    @SerializedName("data")
    val data: StudioBody
)

data class StudioBody(
    val name: String,
    @SerializedName("ownerID")
    val ownerId: Int
)

interface StudioService {
    @POST("studios")
    fun insert(@Body body: StudioData): retrofit2.Call<Studio>

    @GET("studios")
    fun getall() : retrofit2.Call<StudioResponse<List<Studio>>>
}