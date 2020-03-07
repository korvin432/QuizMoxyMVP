package com.mindyapps.android.quizmoxymvp.ui.quiz

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.google.android.material.snackbar.Snackbar

import com.mindyapps.android.quizmoxymvp.R
import com.mindyapps.android.quizmoxymvp.mvp.models.Quiz
import com.mindyapps.android.quizmoxymvp.mvp.presenters.QuizPresenter
import com.mindyapps.android.quizmoxymvp.mvp.views.QuizView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class QuizFragment : MvpAppCompatFragment(), QuizView, View.OnClickListener {

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
    private lateinit var categoryName: String
    private var isTrue: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = arguments!!.getInt("categoryId")
        categoryName = arguments!!.getString("categoryName","")
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
        CoroutineScope(Dispatchers.IO).launch {
            questionsList = presenter!!.getQuizData(categoryId, difficulty)
            presenter!!.questionNumber = 0
        }
    }

    override fun setQuestion() {
        val question = presenter!!.questionsList[presenter!!.questionNumber]
        questionNumberText.text =
            getString(R.string.question_number_text, (presenter!!.questionNumber + 1).toString())
        questionText.text = HtmlCompat.fromHtml(
            question.questionText, HtmlCompat.FROM_HTML_MODE_LEGACY
        ).toString()
        isTrue = question.correctAnswer == "True"
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.true_button -> {
                if (isTrue) {
                    presenter!!.rightAnswers++
                    Snackbar.make(p0, "Right!", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(p0, "Wrong!", Snackbar.LENGTH_SHORT).show()
                }
            }
            R.id.false_button -> {
                if (!isTrue) {
                    presenter!!.rightAnswers++
                    Snackbar.make(p0, "Right!", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(p0, "Wrong!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        presenter!!.questionNumber++
        if (presenter!!.questionNumber < presenter!!.questionsList.size) {
            setQuestion()
        } else {
            questionNumberText.text = getString(R.string.congratulations)
            questionText.text =
                getString(R.string.right_answers) + ": " + presenter!!.rightAnswers.toString()
            falseButton.visibility = View.GONE
            trueButton.visibility = View.GONE
            presenter!!.saveToDB(activity!!.applicationContext, categoryName, difficulty)
        }

    }
}
