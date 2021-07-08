package com.crisav2.challengemeli.product.presenter

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.product.ProductDetail
import com.crisav2.challengemeli.repository.ProductAPI
import com.crisav2.challengemeli.repository.model.asProduct
import com.crisav2.core.usecase.Transformation
import com.crisav2.core.usecase.Validators
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Scheduler

/**
 * ProductDetailPresenter - Presenter
 *
 * Presenter que presenta el detalle del producto que selecciono el usuario
 * View: ProductDetail.View
 */

class ProductDetailPresenter(
    val view: ProductDetail.View,
    val messageManager: IMessageManager,
    private val productAPI: ProductAPI,
    private val validators: Validators,
    private val transformation: Transformation,
    private val subscribeOn: Scheduler,
    private val observeOn: Scheduler
): ProductDetail.Presenter {

    private var productID: String? = null

    private var disposables = CompositeDisposable()

    // Public methods section
    override fun initialize(productID: String?) {
        productID?.let{
            this.productID = productID
            startLoadingProduct()
        }?: run{
            showErrorView()
        }
        view.setUpTryAgainButtonTitle(messageManager.titleTryAgainProductDetail)
    }

    private fun showErrorView() {
        view.setErrorView(messageManager.errorEmptyProductID, false)
        view.showLoading(false)
    }

    override fun destroy() {
        disposables.dispose()
        disposables.clear()
    }

    override fun tryDetailAgain() {
        startLoadingProduct()
    }

    // Private methods section
    private fun startLoadingProduct() {
        productID?.let{
            view.showLoading(true)
            val productDisposable = productAPI.getProducts(it)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .doFinally{view.showLoading(false)}
                .subscribe({ result ->
                    val product = result.asProduct()
                    val isProductValid = validators.validateProduct(product)
                    if(isProductValid){
                        view.setProductInView(product)
                    }else{
                        view.setErrorView(messageManager.errorEmptyProductID, true)
                    }
                },{ error ->
                    view.setErrorView(
                        error.localizedMessage ?: messageManager.errorEmptyProductID,
                        true)
                })
            disposables.add(productDisposable)
        }?: view.setErrorView(messageManager.errorEmptyProductID, false)
    }
}