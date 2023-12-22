package com.example.sewastudio.ui.controller


import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.sewastudio.GoTo
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.ui.model.Auth
import com.example.sewastudio.ui.model.User
import com.example.sewastudio.ui.service.AuthService
import com.example.sewastudio.ui.service.LoginData
import com.example.sewastudio.ui.service.RegisterData
import com.example.sewastudio.ui.service.UserRole
import com.example.sewastudio.ui.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class AuthController {
    companion object {
        private var authService : AuthService = ClientController.getService(AuthService::class.java)

        fun routeLogin(role: UserRole, navController: NavController, preferencesManager: PreferencesManager){
            when (role) {
                UserRole.Pemilik -> {
                    GoTo("pemilikhomepage", navController, preferencesManager)
                }
                UserRole.Karyawan -> {
                    GoTo("karyawanhomepage", navController, preferencesManager)
                }
                else -> {
                    GoTo("pelangganhomepage", navController, preferencesManager)
                }
            }
        }
        fun login(username : String, password: String, navController: NavController, prefMan: PreferencesManager,  callback: (Auth?) -> Unit) {
            authService.login(LoginData(username, password)).enqueue(object :
                Callback<Auth> {
                override fun onResponse(call: Call<Auth>, response: Response<Auth>): Unit =
                    if (response.isSuccessful) {
                        val respBody = response.body()!!
                        val jwt = respBody.jwt
                        val respUser = respBody.user!!
                        val userID = respUser.id.toString()
                        val role = respUser.status
                        prefMan.saveData("jwt", jwt)
                        prefMan.saveData("userID", userID)
                        prefMan.saveData("username", username)
                        prefMan.saveData("password", password)
                        println(role)
                        println("Succesful login")
                        routeLogin(role,navController,prefMan)
                    } else {
                        println("Unsuccesful login")
                        navController.navigate("auth-page")
                        callback(null)
                    }

                override fun onFailure(call: Call<Auth>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun register(email : String, username : String, password: String, navController: NavController, prefMan: PreferencesManager,  callback: (Auth?) -> Unit) {
            authService.register(RegisterData(email, username, password, UserRole.Pelanggan)).enqueue(object :
                Callback<Auth> {
                override fun onResponse(call: Call<Auth>, response: Response<Auth>): Unit =
                    if (response.isSuccessful) {
                        val respBody = response.body()!!
                        val jwt = respBody.jwt
                        val respUser = respBody.user!!
                        val userID = respUser.id.toString()
                        val role = respUser.status
                        prefMan.saveData("jwt", jwt)
                        prefMan.saveData("userID", userID)
                        prefMan.saveData("username", username)
                        prefMan.saveData("password", password)
                        println()
                        println("Succesful register")
                        routeLogin(role,navController,prefMan)
                    } else {
                        println("Unsuccesful register")
                        navController.navigate("auth-page")
                        callback(null)
                    }

                override fun onFailure(call: Call<Auth>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun logout (navController: NavController, prefMan: PreferencesManager) {
            prefMan.saveData("jwt","")
            prefMan.saveData("userID","")
            prefMan.saveData("username","")
            prefMan.saveData("password","")
            GoTo("auth-page", navController, prefMan)
        }
    }
}