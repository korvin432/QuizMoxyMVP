package com.mindyapps.android.quizmoxymvp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mindyapps.android.quizmoxymvp.R
import com.mindyapps.android.quizmoxymvp.db.QuizEntity
import com.mindyapps.android.quizmoxymvp.mvp.presenters.StatisticsPresenter
import com.mindyapps.android.quizmoxymvp.mvp.views.StatisticsView
import com.mindyapps.android.quizmoxymvp.ui.adapters.QuizAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class StatisticsFragment : MvpAppCompatFragment(), StatisticsView {

    @JvmField
    @InjectPresenter
    var presenter: StatisticsPresenter? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuizAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)
        recyclerView = root.findViewById(R.id.quiz_recycler)
        presenter!!.getData(activity!!.applicationContext)
        return root
    }

    override fun setStatisticsText(quizList: List<QuizEntity>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = QuizAdapter(quizList, context)
        }
    }
}