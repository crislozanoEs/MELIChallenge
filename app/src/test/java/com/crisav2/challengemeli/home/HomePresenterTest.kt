package com.crisav2.challengemeli.home

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.home.presenter.HomePresenter
import com.crisav2.challengemeli.home.view.HomeFragment
import com.crisav2.core.usecase.Validators
import io.mockk.mockk
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.BDDMockito.verify
import org.mockito.Mockito
import org.mockito.Mockito.mock


class HomePresenterTest {
    @Mock
    lateinit var homeView: Home.View
    @Mock
    lateinit var messageManager: IMessageManager
    @Mock
    lateinit var validators: Validators

    lateinit var homePresenter: HomePresenter

    lateinit var inputSubjectMock: PublishSubject<String>

    private val scheduler = Schedulers.trampoline()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        inputSubjectMock = PublishSubject.create()
        homePresenter = HomePresenter(
            homeView,
            messageManager,
            validators,
            scheduler,
            scheduler
        )
    }

    @Test
    fun shouldInitialize() {
        homePresenter.initialize()
        verify(homeView).setTextViewTitle(messageManager.searchTextViewTitle)
        verify(homeView).setButtonTitle(messageManager.searchButtonTitle)
        verify(homeView).enableSearchButton(false)
    }

    @Test
    fun shouldEnableButton(){
        homePresenter.initDisposables(inputSubjectMock)
        inputSubjectMock.onNext("keyword")
        verify(homeView).enableSearchButton(true)
    }

    @Test
    fun shouldNotEnableButton(){
        homePresenter.initDisposables(inputSubjectMock)
        inputSubjectMock.onNext("")
        verify(homeView).enableSearchButton(false)
    }

}