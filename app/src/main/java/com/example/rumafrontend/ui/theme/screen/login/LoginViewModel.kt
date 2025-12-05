package com.example.rumafrontend.ui.theme.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rumafrontend.data.model.loginRequest
import com.example.rumafrontend.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loginStatus = MutableStateFlow<String?>(null)
    val loginStatus = _loginStatus.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            val request = loginRequest(email, password)

            val result = authRepository.login(request)

            if (result == "Success") {
                _loginStatus.value = "Success"
            } else {
                _loginStatus.value = result
            }

            _loading.value = false
        }
    }
}
