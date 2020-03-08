package com.mindyapps.android.quizmoxymvp.mvp.views

import com.mindyapps.android.quizmoxymvp.db.QuizEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface StatisticsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setStatisticsText(quizList: List<QuizEntity>)

}