package com.crisav2.challengemeli.home.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.crisav2.challengemeli.MainActivity
import com.crisav2.challengemeli.R
import com.crisav2.challengemeli.common.applicationComponent
import com.crisav2.challengemeli.home.Home
import com.crisav2.challengemeli.home.di.HomeModule
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), Home.View {

    lateinit var searchButton: Button
    lateinit var searchEditText: EditText
    lateinit var searchTextView: TextView

    override val inputSubject: PublishSubject<String> = PublishSubject.create()

    @Inject
    lateinit var presenter: Home.Presenter


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        setUpView(root)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        presenter.initialize()
    }

    private fun initListeners() {
        editTextSearch.addTextChangedListener(ValidateInputWatcher())
        buttonSearch.setOnClickListener {
            presenter.authorizeNextFragment()
        }
    }

    inner class ValidateInputWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            inputSubject.onNext(s.toString())
        }
    }

    private fun setUpView(root: View) {
        searchButton = root.findViewById(R.id.buttonSearch)
        searchEditText = root.findViewById(R.id.editTextSearch)
        searchTextView = root.findViewById(R.id.textViewSearchTitle)
    }


    override fun goListScreen(keyword: String) {
        searchEditText.isFocusable = false
        searchButton.isFocusable = false
        (activity as MainActivity).goToList(keyword)
    }

    override fun enableSearchButton(enabled: Boolean) {
        searchButton.isEnabled = enabled
    }

    override fun setTextViewTitle(title: String) {
        searchTextView.text = title
    }

    override fun setButtonTitle(title: String) {
        searchButton.text = title
    }

    override fun setInput(input: String) {
        searchEditText.setText(input)
    }

    private fun inject() {
        applicationComponent
            .plus(HomeModule(this))
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clear()
    }
}