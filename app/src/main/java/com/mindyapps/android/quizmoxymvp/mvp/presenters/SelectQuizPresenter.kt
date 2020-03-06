package com.mindyapps.android.quizmoxymvp.mvp.presenters

import android.util.Log
import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import com.mindyapps.android.quizmoxymvp.mvp.views.SelectQuizView
import com.mindyapps.android.quizmoxymvp.network.QuizApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SelectQuizPresenter : MvpPresenter<SelectQuizView>() {

    var selectedPosition = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        CoroutineScope(IO).launch {
            getCategories()
        }
    }

     private suspend fun getCategories() {
        val quizApi = QuizApi()
        val currentCategoryResult = quizApi.getCategories()
         withContext(Main){
             viewState.makeSpinnerClickable(currentCategoryResult.category)
         }
    }

}