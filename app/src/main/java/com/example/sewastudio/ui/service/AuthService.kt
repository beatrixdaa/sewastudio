package com.example.sewastudio.ui.service


import com.example.sewastudio.ui.model.Auth
import retrofit2.http.Body
import retrofit2.http.POST

enum class UserRole {
    Pelanggan,
    Pemilik,
    Karyawan
}
data class LoginData (val identifier:String, val password: String)
data class RegisterData (val email:String, val username:String, val password: String, val status: UserRole)

interface AuthService {
    @POST("auth/local")
    fun login(@Body body: LoginData) : retrofit2.Call<Auth>
    @POST("auth/local/register")
    fun register(@Body body: RegisterData) : retrofit2.Call<Auth>
}