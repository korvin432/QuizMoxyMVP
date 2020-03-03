package com.mindyapps.android.quizmoxymvp.ui.select_quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.mindyapps.android.quizmoxymvp.R
import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import com.mindyapps.android.quizmoxymvp.mvp.views.SelectQuizView
import com.mindyapps.android.quizmoxymvp.network.QuizApi
import com.mindyapps.android.quizmoxymvp.mvp.presenters.SelectQuizPresenter
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class SelectQuizFragment : MvpAppCompatFragment(), SelectQuizView {

    @JvmField
    @InjectPresenter
    var presenter: SelectQuizPresenter? = null

    lateinit var categorySpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_select_quiz, container, false)

        categorySpinner = root.findViewById(R.id.category_spinner)
        setCategoryItems()

        return root
    }

    override fun setCategoryItems() {
        var categoryData: List<String>
        CoroutineScope(Main).launch {
            categoryData = presenter!!.getCategories()

            val adapter = ArrayAdapter<String>(
                activity!!.applicationContext, R.layout.spinner_style, categoryData
            )
            adapter.setDropDownViewResource(R.layout.spinner_style)

            withContext(Main){
                categorySpinner.adapter = adapter
            }
        }
    }

}