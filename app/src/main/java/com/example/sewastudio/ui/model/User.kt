package com.example.sewastudio.ui.model

import com.example.sewastudio.ui.service.UserRole
import com.google.gson.annotations.SerializedName

class User {
    var id: Int = 0
    var username : String = ""
    var email : String = ""
    var provider : String = ""
    var confirmed: String = ""
    var blocked: Boolean = false
    var createdAt : String = ""
    var updatedAt: String = ""
    @SerializedName("status")
    var status: UserRole = UserRole.Pelanggan
}