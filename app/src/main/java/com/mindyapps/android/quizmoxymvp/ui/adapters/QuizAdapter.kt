package com.mindyapps.android.quizmoxymvp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mindyapps.android.quizmoxymvp.R
import com.mindyapps.android.quizmoxymvp.db.QuizEntity
import kotlinx.android.synthetic.main.quiz_item.view.*

class QuizAdapter(val quizList: List<QuizEntity>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return quizList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.quizCategory.text = quizList[position].category
        holder.quizDifficulty.text = quizList[position].difficulty
        holder.quizRightAnswers.text = context.getString(R.string.right_answers) + ": " +
                quizList[position].rightAnswers.toString()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val quizCategory: TextView = view.quiz_category
    val quizDifficulty: TextView = view.quiz_difficulty
    val quizRightAnswers: TextView = view.quiz_right_answers
}