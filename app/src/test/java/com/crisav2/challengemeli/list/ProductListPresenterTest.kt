package com.crisav2.challengemeli.list

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.list.presenter.ProductListPresenter
import com.crisav2.challengemeli.list.view.ProductListFragment
import com.crisav2.challengemeli.repository.SearchAPI
import com.crisav2.challengemeli.repository.model.SearchResultNetwork
import com.crisav2.core.usecase.Transformation
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.CompletableSubject
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ProductListPresenterTest {

    @Mock
    lateinit var view: ProductList.View
    @Mock
    lateinit var messageManager: IMessageManager

    @Mock
    lateinit var searchAPI: SearchAPI
    @Mock
    lateinit var  transformation: Transformation

    private lateinit var productListPresenter: ProductListPresenter

    private val scheduler = Schedulers.trampoline()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(searchAPI.getProducts("keyword")).thenReturn(Single.just(SearchResultNetwork()))
        productListPresenter = ProductListPresenter(
            view,
            messageManager,
            searchAPI,
            transformation,
            scheduler,
            scheduler
        )
    }

    @Test
    fun shouldInitSearch(){
        productListPresenter.initialize("keyword")
        verify(view).showLoading(true)
        verify(view).setTitle("${messageManager.titleProductListScreen} keyword")
    }

    @Test
    fun shouldInitError(){
        productListPresenter.initialize(null)
        verify(view).showLoading(false)
        verify(view).showError(messageManager.errorEmptySearch)
    }

    @Test
    fun shouldShowGoNextFragment(){
        val id = "Some_id"
        productListPresenter.validateIdAndNavigate(id)
        verify(view).goToProductDetail(id)
    }
}