package com.crisav2.challengemeli.home.presenter

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.home.Home
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.util.Log
import com.crisav2.core.usecase.Validators
import io.reactivex.Scheduler
import io.reactivex.subjects.PublishSubject

class HomePresenter (
    private val view: Home.View,
    private val messageManager: IMessageManager,
    private val validators: Validators,
    private val subscribeOn: Scheduler,
    private val observeOn: Scheduler
): Home.Presenter {

    private var currentUserInput: String = ""

    private val disposables = CompositeDisposable()

    override fun initialize() {
        loadView()
        view.initListeners()
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

  override fun initDisposables(inputSubject: PublishSubject<String>) {
        val changeInputDisposable = inputSubject
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
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