package com.example.retrofitexample.ui.books

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.network.Api
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private var _bookList = MutableLiveData<List<BookModel>>()
    val bookList: LiveData<List<BookModel>>
        get() = _bookList

    fun getBookList() {
        viewModelScope.launch {
            try {
                _bookList.value = Api.retrofitService.getBooks("fiction", 20)
                Log.e("BooksViewModel", "DONE getBookList: "+ _bookList.value!!.size)

            } catch (e: Exception) {
                //Toast.makeText(activity, "Error Submitted Order", Toast.LENGTH_SHORT).show()
                Log.e("BooksViewModel", "ERROR submitBookOrder: " + e.message.toString())
            }
        }
    }
}