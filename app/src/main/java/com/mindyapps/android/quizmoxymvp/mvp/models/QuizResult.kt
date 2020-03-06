package com.mindyapps.android.quizmoxymvp.mvp.models

import com.google.gson.annotations.SerializedName

data class QuizResult(
    @SerializedName("results")
    val quiz: List<Quiz>
)