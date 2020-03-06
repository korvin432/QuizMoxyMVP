package com.mindyapps.android.quizmoxymvp.mvp.models

import com.google.gson.annotations.SerializedName


data class Quiz(
    @SerializedName("question")
    val questionText: String,
    @SerializedName("correct_answer")
    val correctAnswer: String
)