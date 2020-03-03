package com.mindyapps.android.quizmoxymvp.mvp.models

import com.google.gson.annotations.SerializedName

data class CategoryResult(
    @SerializedName("trivia_categories")
    val category: List<Category>
)