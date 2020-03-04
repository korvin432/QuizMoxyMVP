package com.mindyapps.android.quizmoxymvp.ui.select_quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import com.mindyapps.android.quizmoxymvp.R
import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import com.mindyapps.android.quizmoxymvp.mvp.views.SelectQuizView
import com.mindyapps.android.quizmoxymvp.network.QuizApi
import com.mindyapps.android.quizmoxymvp.mvp.presenters.SelectQuizPresenter
import com.mindyapps.android.quizmoxymvp.ui.adapters.SpinnerAdapter
import kotlinx.android.synthetic.main.fragment_select_quiz.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class SelectQuizFragment : MvpAppCompatFragment(), SelectQuizView, AdapterView.OnItemSelectedListener {

    @JvmField
    @InjectPresenter
    var presenter: SelectQuizPresenter? = null

    private lateinit var categorySpinner: Spinner
    private lateinit var difficultySpinner: Spinner
    private lateinit var adapter: SpinnerAdapter
    private lateinit var startButton: Button
    private var categoryId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_select_quiz, container, false)

        categorySpinner = root.findViewById(R.id.category_spinner)
        difficultySpinner = root.findViewById(R.id.difficulty_spinner)
        startButton = root.findViewById(R.id.start_button)

        categorySpinner.onItemSelectedListener = this
        setCategoryItems()

        startButton.setOnClickListener {
            Log.d("qwwe", "Selected quiz ${categorySpinner.selectedItem} " +
                    "with id $select_category and dif ${difficultySpinner.selectedItem}")
        }

        return root
    }


    override fun setCategoryItems() {
        CoroutineScope(Main).launch {
            adapter = SpinnerAdapter(activity!!.applicationContext, presenter!!.getCategories())
            withContext(Main){
                categorySpinner.adapter = adapter
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p0!!.id){
            R.id.category_spinner -> {
                val category = adapter.getItem(p2)
                categoryId = category.id
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}