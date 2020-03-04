package com.mindyapps.android.quizmoxymvp.mvp.presenters

import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import com.mindyapps.android.quizmoxymvp.mvp.views.SelectQuizView
import com.mindyapps.android.quizmoxymvp.network.QuizApi
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SelectQuizPresenter : MvpPresenter<SelectQuizView>() {

    var list: List<Category> = ArrayList()

    suspend fun getCategories(): List<Category> {
        val quizApi = QuizApi()
       // val names: MutableList<String> = ArrayList()
        val currentCategoryResult = quizApi.getCategoriesAsync()
        list = currentCategoryResult.category
        //list.forEach { names.add(it.name) }
        return list
    }

}