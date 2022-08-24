package com.example.retrofitexample.ui.books

data class BookModel(var id        : Int?     = null,
                     var name      : String?  = null,
                     var type      : String?  = null,
                     var available : Boolean? = null)