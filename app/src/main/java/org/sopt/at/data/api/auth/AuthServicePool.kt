package org.sopt.at.data.api.auth

import org.sopt.at.di.RetrofitInstance

object AuthServicePool {
    val authService: AuthService by lazy {
        RetrofitInstance.create<AuthService>()
    }
}