package com.mindyapps.android.quizmoxymvp.mvp.presenters

import android.content.Context
import com.mindyapps.android.quizmoxymvp.db.QuizDatabase
import com.mindyapps.android.quizmoxymvp.db.QuizEntity
import com.mindyapps.android.quizmoxymvp.mvp.models.Quiz
import com.mindyapps.android.quizmoxymvp.mvp.views.QuizView
import com.mindyapps.android.quizmoxymvp.mvp.views.StatisticsView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.*

@InjectViewState
class StatisticsPresenter : MvpPresenter<StatisticsView>()  {
    var quizList: List<QuizEntity> = LinkedList()

    fun getData(context: Context){
        val db = QuizDatabase(context)
        CoroutineScope(Dispatchers.IO).launch {
            quizList = db.quizDao().getAll()
            withContext(Main){
                viewState.setStatisticsText(quizList)
            }
        }
    }

}