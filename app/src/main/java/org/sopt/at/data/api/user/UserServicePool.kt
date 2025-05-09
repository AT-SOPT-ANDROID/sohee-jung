package org.sopt.at.data.api.user

import org.sopt.at.data.RetrofitInstance

object UserServicePool {
    val userService: UserService by lazy {
        RetrofitInstance.create<UserService>()
    }
}