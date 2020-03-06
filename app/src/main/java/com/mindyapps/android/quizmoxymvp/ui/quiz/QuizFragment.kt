package com.mindyapps.android.quizmoxymvp.ui.quiz

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat

import com.mindyapps.android.quizmoxymvp.R
import com.mindyapps.android.quizmoxymvp.mvp.models.Quiz
import com.mindyapps.android.quizmoxymvp.mvp.presenters.QuizPresenter
import com.mindyapps.android.quizmoxymvp.mvp.presenters.SelectQuizPresenter
import com.mindyapps.android.quizmoxymvp.mvp.views.QuizView
import com.mindyapps.android.quizmoxymvp.ui.adapters.SpinnerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class QuizFragment : MvpAppCompatFragment(), QuizView, View.OnClickListener {

    //TODO: Заменить 9 на константу

    @JvmField
    @InjectPresenter
    var presenter: QuizPresenter? = null

    private lateinit var difficulty: String
    private lateinit var questionNumberText: TextView
    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var questionsList: List<Quiz>

    private var categoryId = 0
    private var questionNumber = 0
    private var rightAnswers = 0
    private var isTrue: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = arguments!!.getInt("categoryId")
        difficulty = arguments!!.getString("difficulty", "easy")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_quiz, container, false)
        questionNumberText = root.findViewById(R.id.question_number)
        questionText = root.findViewById(R.id.question_text)
        trueButton = root.findViewById(R.id.true_button)
        falseButton = root.findViewById(R.id.false_button)

        trueButton.setOnClickListener(this)
        falseButton.setOnClickListener(this)
        return root
    }

    override fun setQuizData() {
        Log.d("qwwe", "setQuizData")
        CoroutineScope(Dispatchers.Main).launch {
            questionsList = presenter!!.getQuizData(categoryId, difficulty)
        }
    }

    override fun setQuestion(list: List<Quiz>) {
        Log.d("qwwe", "setQuestion")
        val question = list[questionNumber]
        questionNumberText.text = getString(R.string.question_number_text,(questionNumber + 1).toString())
        questionText.text = HtmlCompat.fromHtml(
            question.questionText, HtmlCompat.FROM_HTML_MODE_LEGACY
        ).toString()
        isTrue = question.correctAnswer == "True"
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.true_button -> {
                if (isTrue) {
                    Log.d("qwwe", "GOOD JOB")
                    rightAnswers++
                } else {
                    Log.d("qwwe", "BAD JOB")
                }
            }
            R.id.false_button -> {
                if (!isTrue) {
                    Log.d("qwwe", "GOOD JOB")
                    rightAnswers++
                } else {
                    Log.d("qwwe", "BAD JOB")
                }
            }
        }

        questionNumber++
        if (questionNumber <= 9) {
            setQuestion(questionsList)
        } else {
            questionNumberText.text = getString(R.string.congratulations)
            questionText.text = getString(R.string.right_answers) + " " + rightAnswers.toString()
        }

    }
}
