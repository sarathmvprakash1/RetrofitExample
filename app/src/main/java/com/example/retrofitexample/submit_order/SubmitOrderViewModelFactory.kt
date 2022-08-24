package com.example.retrofitexample.submit_order

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SubmitOrderViewModelFactory(private val activity: Activity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubmitOrderViewModel::class.java)) {
            return SubmitOrderViewModel(activity = activity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}