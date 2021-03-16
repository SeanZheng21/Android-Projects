package com.example.vocabulary

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.fragment_item_quiz.view.*
import kotlinx.android.synthetic.main.item_detail.view.*


class ItemQuizFragment : Fragment() {
    private var item: VocabularyContent.WordItem? = null
    private var answer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = VocabularyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                activity?.toolbar_layout?.title = item?.content
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_item_quiz, container, false)
        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.item_quiz.text = "Translate " + it.content
            rootView.quizAnswerTextView.text = ""
            answer = it.details.trim()
            rootView.quizCheckButton.setOnClickListener {
                val txt = getView()?.answerEditText?.text.toString()
                Log.d("compareanswer", "Comparing " +
                        txt + " to " + answer)
                if (txt == answer) {
                    getView()?.quizAnswerTextView!!.text = "Correct, you are the best!"
                } else {
                    getView()?.quizAnswerTextView!!.text = "Incorrect, the answer is: $answer."
                }
            }
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

}
