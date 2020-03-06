package com.mindyapps.android.quizmoxymvp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mindyapps.android.quizmoxymvp.mvp.models.CategoryResult
import com.mindyapps.android.quizmoxymvp.mvp.models.Quiz
import com.mindyapps.android.quizmoxymvp.mvp.models.QuizResult
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface QuizApi{

    @GET("/api_category.php")
    suspend fun getCategories(): CategoryResult

    @GET("/api.php?amount=10&type=boolean")
    suspend fun getQuizResult(
        @Query("category") categoryId: Int,
        @Query("difficulty") difficulty: String
    ): QuizResult

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