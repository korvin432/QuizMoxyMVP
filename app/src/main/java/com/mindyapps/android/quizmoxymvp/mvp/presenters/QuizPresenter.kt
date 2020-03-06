package com.mindyapps.android.quizmoxymvp.mvp.presenters

import android.util.Log
import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import com.mindyapps.android.quizmoxymvp.mvp.models.Quiz
import com.mindyapps.android.quizmoxymvp.mvp.views.QuizView
import com.mindyapps.android.quizmoxymvp.mvp.views.SelectQuizView
import com.mindyapps.android.quizmoxymvp.network.QuizApi
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class QuizPresenter : MvpPresenter<QuizView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setQuizData()
    }

    suspend fun getQuizData(id: Int, difficulty: String): List<Quiz> {
        val quizApi = QuizApi()
        val currentQuizResult = quizApi.getQuizResult(id, difficulty)
        viewState.setQuestion(currentQuizResult.quiz)
        return currentQuizResult.quiz
    }

}