package com.example.retrofitexample.submit_order

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.network.Api
import kotlinx.coroutines.launch

class SubmitOrderViewModel(private val activity: Activity) : ViewModel() {
    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var sharedPreferences: SharedPreferences

    private var _submitBookOrderResult = MutableLiveData<SubmitBookOrderResultModel>()
    val submitBookOrderResult: LiveData<SubmitBookOrderResultModel>
        get() = _submitBookOrderResult

    fun submitBookOrder(bookId: Int, name: String) {


        sharedPreferences = activity.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)!!

        val sharedPreferences: SharedPreferences = activity.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)!!
        viewModelScope.launch {
            try {
                val token = "Bearer "+sharedPreferences.getString("ACCESS_TOKEN_KEY", "")
                Log.e("SubmitOrderViewModel", " token: "+token
                        +": bookId: "+bookId+" :name: "+name)
                _submitBookOrderResult.value = Api.retrofitService.
                submitBookOrder(token, SubmitBookOrderModel(bookId, name))
                Log.e("SubmitOrderViewModel", "DONE result: "+_submitBookOrderResult.toString())


                //TODO:Clear Id and Name EditText
            } catch (e:Exception) {
                Toast.makeText(activity, "Error Submitted Order", Toast.LENGTH_SHORT).show()
                Log.e("SubmitOrderViewModel", "ERROR submitBookOrder: "+e.message.toString())
            }
        }
    }
}