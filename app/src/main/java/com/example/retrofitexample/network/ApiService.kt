package com.example.retrofitexample.network

import com.example.retrofitexample.submit_order.SubmitBookOrderModel
import com.example.retrofitexample.submit_order.SubmitBookOrderResultModel
import com.example.retrofitexample.ui.books.BookModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private val BASE_URL = "https://simple-books-api.glitch.me"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {

    @POST("/api-clients/")
    suspend fun getAuthenticationToken(@Body authentication: Authentication): AuthenticationResultSuccess

    //'type' = fiction, non-fiction
    @GET("/books")
    suspend fun getBooks(@Query("type") type: String, @Query("limit") limit: Int): List<BookModel>

    @POST("/orders")
    suspend fun submitBookOrder(@Header("Authorization") Authorization:String,
                                @Body submitBookOrderModel: SubmitBookOrderModel): SubmitBookOrderResultModel

    /*@POST("/orders")
    suspend fun submitBookOrder(@Header("Authorization") Authorization:String, @Body submitBookOrderModel: SubmitBookOrderModel): SubmitBookOrderResultModel
*/
}

object Api {
    val retrofitService : MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}