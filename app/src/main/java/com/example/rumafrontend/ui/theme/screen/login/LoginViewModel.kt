package com.example.rumafrontend.ui.theme.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rumafrontend.data.model.loginRequest
import com.example.rumafrontend.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class  LoginViewModel : ViewModel(){
    private  val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loginStatus = MutableStateFlow<String?>(null)
    val loginStatus = _loginStatus.asStateFlow()

    fun login (email: String, password : String){
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = ApiClient.apiService.login(
                    loginRequest(email, password)
                )
                if (response.status == "Success"){
                    _loginStatus.value = "Success"
                }else{
                    _loginStatus.value = response.message?: "Login Gagal!"
                }
            } catch (e: Exception){
                _loginStatus.value = "Error: ${e.message}"
            }
            _loading.value = false
        }
    }
}
