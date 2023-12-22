package com.example.sewastudio.ui.karyawan

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.ui.controller.AuthController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KaryawanHomePage(navController: NavController, modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Sewa Studio", modifier = Modifier, color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {
                    AuthController.logout(navController, preferencesManager)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
            ) {
                Text(text = "Logout")
            }
        }
    }
}