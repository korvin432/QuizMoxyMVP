package com.mindyapps.android.quizmoxymvp.mvp.views

import com.mindyapps.android.quizmoxymvp.mvp.models.Quiz
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

interface QuizView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setQuestion(list: List<Quiz>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setQuizData()

}