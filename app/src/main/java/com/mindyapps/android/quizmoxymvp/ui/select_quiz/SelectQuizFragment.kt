package com.mindyapps.android.quizmoxymvp.ui.select_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mindyapps.android.quizmoxymvp.R

class SelectQuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_select_quiz, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        textView.text = getString(R.string.title_select_quiz)
        return root
    }
}