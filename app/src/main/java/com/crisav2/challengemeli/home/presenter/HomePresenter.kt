package com.crisav2.challengemeli.home.presenter

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.home.Home
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.util.Log
import com.crisav2.core.usecase.Validators

class HomePresenter (
    private val view: Home.View,
    private val messageManager: IMessageManager,
    private val validators: Validators
): Home.Presenter {

    private var currentUserInput: String = ""

    private val disposables = CompositeDisposable()

    override fun initialize() {
        loadView()
        initDisposables()
    }

    override fun clear() {
        disposables.dispose()
        disposables.clear()
    }

  override fun authorizeNextFragment() {
    if(currentUserInput.isNotEmpty()){
      view.goListScreen(currentUserInput)
    }
  }

  private fun initDisposables() {
        val changeInputDisposable = view.inputSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                  currentUserInput = it
                  val isValidInput = validators.validateKeyword(currentUserInput)
                  view.enableSearchButton(isValidInput)
                },{})
        disposables.add(changeInputDisposable)
    }

    private fun loadView() {
      view.setTextViewTitle(messageManager.searchTextViewTitle)
      view.setButtonTitle(messageManager.searchButtonTitle)
      view.enableSearchButton(currentUserInput.isNotEmpty())
    }


}