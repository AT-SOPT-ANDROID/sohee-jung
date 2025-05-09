package org.sopt.at.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.at.local.datastore.UserLocalDataStore
import org.sopt.at.repository.UserRepository

class MyViewModel(
    private val userLocalDataStore: UserLocalDataStore
) : ViewModel() {
    private val _nickname: MutableStateFlow<MyState> = MutableStateFlow(MyState())
    val nickname: StateFlow<MyState> = _nickname.asStateFlow()

    private val userRepository = UserRepository()

    fun getMyNickname() {
        viewModelScope.launch {
            val userId = userLocalDataStore.signInUserModel.first().userId
            val result = userRepository.getMyNickname(userId)

            _nickname.value = if (result.isSuccess) {
                _nickname.value.copy(
                    nickName = result.getOrNull()?.nickname ?: ""
                )
            } else {
                _nickname.value.copy(
                    nickName = "불러오기 실패"
                )
            }
        }

    }
}