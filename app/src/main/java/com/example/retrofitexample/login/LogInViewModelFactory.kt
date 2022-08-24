package com.example.retrofitexample.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.*


class LogInViewModelFactory(private val application: Application): Factory {
  /*  override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //return super.create(modelClass)
        if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
            return LogInViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }*/

    /*@Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
            return LogInViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }*/


}
