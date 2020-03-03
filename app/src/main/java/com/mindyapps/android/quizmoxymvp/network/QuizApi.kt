package com.mindyapps.android.quizmoxymvp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mindyapps.android.quizmoxymvp.mvp.models.CategoryResult
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface QuizApi{

    @GET("/api_category.php")
    suspend fun getCategoriesAsync(): CategoryResult

    companion object{
        operator fun invoke(): QuizApi{
            val okHttpClient = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://opentdb.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuizApi::class.java)
        }
    }
}