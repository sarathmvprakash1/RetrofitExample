package com.example.retrofitexample.login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.network.Api
import com.example.retrofitexample.network.Authentication
import com.example.retrofitexample.network.AuthenticationResultSuccess
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    private val _accessToken = MutableLiveData<AuthenticationResultSuccess>()
    val accessToken: LiveData<AuthenticationResultSuccess>
        get() = _accessToken

    fun getAuthenticationToken() {
        viewModelScope.launch {
            //_status.value = MarsApiStatus.LOADING
            try {
                _accessToken.value = Api.retrofitService.getAuthenticationToken(
                    Authentication("sarath", "sarath0pppp32112345@gmail.com"))
                Log.e("LogInViewModel", "DONE getAuthenticationToken: "+_accessToken.value)
                //_status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                //_status.value = MarsApiStatus.ERROR
                //_accessToken.value = String()

                Log.e("LogInViewModel", "ERROR getAuthenticationToken: "+e.message.toString())
            }
        }
    }
}