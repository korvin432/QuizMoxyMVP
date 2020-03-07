package com.mindyapps.android.quizmoxymvp.ui.select_quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mindyapps.android.quizmoxymvp.R
import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import com.mindyapps.android.quizmoxymvp.mvp.views.SelectQuizView
import com.mindyapps.android.quizmoxymvp.mvp.presenters.SelectQuizPresenter
import com.mindyapps.android.quizmoxymvp.ui.adapters.SpinnerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SelectQuizFragment : MvpAppCompatFragment(), SelectQuizView {

    @JvmField
    @InjectPresenter
    var presenter: SelectQuizPresenter? = null

    private lateinit var categorySpinner: Spinner
    private lateinit var difficultySpinner: Spinner
    private lateinit var adapter: SpinnerAdapter
    private lateinit var startButton: Button

    lateinit var navController: NavController
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

        startButton.setOnClickListener {
            val bundle = bundleOf(
                "categoryId" to categoryId,
                "categoryName" to categorySpinner.selectedItem.toString(),
                "difficulty" to difficultySpinner.selectedItem.toString().toLowerCase()
            )
            navController.navigate(
                R.id.action_navigation_select_quiz_to_quizFragment,
                bundle
            )
        }

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                categoryId = adapter.getItem(p2).id
                presenter!!.selectedPosition = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun makeSpinnerClickable(list: List<Category>) {
        adapter = SpinnerAdapter(activity!!.applicationContext, list)
        categorySpinner.adapter = adapter
        categorySpinner.setSelection(presenter!!.selectedPosition)
    }

}