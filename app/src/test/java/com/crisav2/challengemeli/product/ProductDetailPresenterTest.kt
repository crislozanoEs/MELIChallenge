package com.crisav2.challengemeli.product

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.product.presenter.ProductDetailPresenter
import com.crisav2.challengemeli.repository.ProductAPI
import com.crisav2.challengemeli.repository.model.ProductNetwork
import com.crisav2.core.usecase.Transformation
import com.crisav2.core.usecase.Validators
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ProductDetailPresenterTest {
    @Mock
    lateinit var view: ProductDetail.View
    @Mock
    lateinit var messageManager: IMessageManager
    @Mock
    lateinit var productAPI: ProductAPI
    @Mock
    lateinit var validators: Validators
    @Mock
    lateinit var transformation: Transformation

    private lateinit var presenter: ProductDetailPresenter

    private val scheduler = Schedulers.trampoline()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        `when`(productAPI.getProducts("id"))
            .thenReturn(Single.just(ProductNetwork()))
        presenter = ProductDetailPresenter(
            view,
            messageManager,
            productAPI,
            validators,
            transformation,
            scheduler,
            scheduler
        )
    }

    @Test
    fun shouldInitialize(){
        val id = "id"
        presenter.initialize(id)
        verify(view).showLoading(true)
        verify(view).setUpTryAgainButtonTitle(messageManager.titleTryAgainProductDetail)
    }

    @Test
    fun shouldInitializeWithError(){
        presenter.initialize(null)
        verify(view).showLoading(false)
        verify(view).setErrorView(messageManager.errorEmptyProductID, false)
        verify(view).setUpTryAgainButtonTitle(messageManager.titleTryAgainProductDetail)
    }
}