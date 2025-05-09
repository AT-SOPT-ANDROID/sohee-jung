package org.sopt.at.data.api.auth

import org.sopt.at.data.RetrofitInstance

object AuthServicePool {
    val authService: AuthService by lazy {
        RetrofitInstance.create<AuthService>()
    }
}