package com.example.sewastudio.ui.service


import com.example.sewastudio.ui.model.User

import retrofit2.http.GET

data class UserData (val username:String)
interface UserService {
    @GET("users")
    fun getUsers() : retrofit2.Call<List<User>>
}