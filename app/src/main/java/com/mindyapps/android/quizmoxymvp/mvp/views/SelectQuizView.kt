package com.mindyapps.android.quizmoxymvp.mvp.views

import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import kotlinx.coroutines.Deferred
import moxy.MvpView
import moxy.viewstate.strategy.*
import moxy.viewstate.strategy.alias.AddToEndSingle

interface SelectQuizView : MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun makeSpinnerClickable(list: List<Category>)
}