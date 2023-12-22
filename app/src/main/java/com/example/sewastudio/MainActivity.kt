package com.example.sewastudio


import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sewastudio.ui.controller.AuthController
import com.example.sewastudio.ui.karyawan.KaryawanHomePage
import com.example.sewastudio.ui.pelanggan.PelangganHomePage
import com.example.sewastudio.ui.pemilik.CreateStudioPage
import com.example.sewastudio.ui.pemilik.PemilikHomePage
import com.example.sewastudio.ui.theme.SewastudioTheme
import com.example.sewastudio.ui.view.AuthPage
import com.example.sewastudio.ui.view.SplashPage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val preferencesManager = PreferencesManager(context = LocalContext.current)
            val sharedPreferences: SharedPreferences =
                LocalContext.current.getSharedPreferences("auth", MODE_PRIVATE)
            val navController = rememberNavController()
            var startDestination = "splash"
            var userID = sharedPreferences.getString("userID", "")
            var username = sharedPreferences.getString("username", "")
            var password = sharedPreferences.getString("password", "")

            val loginComplete = remember { mutableStateOf(false) }
//            cek login secara otomatis apabila terdapat username dan password dari session sebelumnya.

            if (!loginComplete.value) {
                if (username != null && password != null) {
                    AuthController.login(
                        username,
                        password,
                        navController,
                        preferencesManager
                    ) { success ->
                        if (success != null) {
                            loginComplete.value = true
                        }
                    }
                } else {
                    navController.navigate("auth-page")
                }
            }

            SewastudioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
            NavHost(navController, startDestination = startDestination) {
                composable(route = "splash") {
                    SplashPage(navController)
                }
                composable(route = "auth-page") {
                    AuthPage(navController)
                }
                composable(route = "pelangganhomepage") {
                    PelangganHomePage(navController)
                }
                composable(route = "karyawan") {
                    KaryawanHomePage(navController)
                }
                composable(route = "pemilikhomepage") {
                    PemilikHomePage(navController)
                }
                composable(route = "createstudiopage") {
                    CreateStudioPage(navController)
                }
            }
        }
    }
}
