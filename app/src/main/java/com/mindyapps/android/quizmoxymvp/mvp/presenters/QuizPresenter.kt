package com.mindyapps.android.quizmoxymvp.mvp.presenters

import android.content.Context
import android.util.Log
import com.mindyapps.android.quizmoxymvp.db.QuizDatabase
import com.mindyapps.android.quizmoxymvp.db.QuizEntity
import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import com.mindyapps.android.quizmoxymvp.mvp.models.Quiz
import com.mindyapps.android.quizmoxymvp.mvp.views.QuizView
import com.mindyapps.android.quizmoxymvp.mvp.views.SelectQuizView
import com.mindyapps.android.quizmoxymvp.network.QuizApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.*

@InjectViewState
class QuizPresenter : MvpPresenter<QuizView>() {
    var questionNumber = 0
    var rightAnswers = 0
    var quizDone = false
    var questionsList: List<Quiz> = LinkedList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setQuizData()
    }

    suspend fun setQuizData(id: Int, difficulty: String) {
        val quizApi = QuizApi()
        val currentQuizResult = quizApi.getQuizResult(id, difficulty.toLowerCase())
        questionsList = currentQuizResult.quiz
        withContext(Main) {
            viewState.setQuestion()
        }
    }

    suspend fun saveToDB(context: Context, categoryName: String, difficulty: String) {
        val db = QuizDatabase(context)
        CoroutineScope(IO).launch {
            db.quizDao().insertAll(QuizEntity(null, categoryName, difficulty, rightAnswers))
        }
        withContext(Main){
            viewState.hideSaveButton()
        }
    }

}