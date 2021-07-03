package com.crisav2.challengemeli.home

import android.content.Context
import io.reactivex.subjects.PublishSubject

interface Home {
    interface View{

        val inputSubject:PublishSubject<String>
        fun goListScreen(keyword: String)
        fun enableSearchButton(enabled: Boolean)
        fun setTextViewTitle(title: String)
        fun setButtonTitle(title: String)
        fun setInput(input: String)

    }
    interface Presenter{
        fun initialize()
        fun clear()
        fun authorizeNextFragment()
    }

}