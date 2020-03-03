package com.mindyapps.android.quizmoxymvp.mvp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SelectQuizView : MvpView {

    @AddToEndSingle
    fun setCategoryItems()

}