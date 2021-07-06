package com.crisav2.challengemeli.product.presenter

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.product.ProductDetail
import com.crisav2.challengemeli.repository.ProductAPI
import com.crisav2.challengemeli.repository.model.asProduct
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.util.Log

class ProductDetailPresenter(
    val view: ProductDetail.View,
    val messageManager: IMessageManager,
    private val productAPI: ProductAPI
): ProductDetail.Presenter {

    private var productID: String? = null

    private var disposables = CompositeDisposable()

    override fun initialize(productID: String?) {
        productID?.let{
            this.productID = productID
            startLoadingProduct()
        }?: run{
            view.setErrorView(messageManager.errorEmptyProductID, false)
            view.showLoading(false)
        }
        view.setUpTryAgainButtonTitle(messageManager.titleTryAgainProductDetail)
    }

    override fun destroy() {
        disposables.dispose()
        disposables.clear()
    }

    override fun tryDetailAgain() {
        startLoadingProduct()
    }

    private fun startLoadingProduct() {
        productID?.let{
            view.showLoading(true)
            val productDisposable = productAPI.getProducts(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally{view.showLoading(false)}
                .subscribe({ result ->
                    val product = result.asProduct()
                    view.setProductInView(product)
                },{ error ->
                    view.setErrorView(
                        error.localizedMessage ?: messageManager.errorEmptyProductID,
                        true)
                })
            disposables.add(productDisposable)
        }?: view.setErrorView(messageManager.errorEmptyProductID, false)
    }


}