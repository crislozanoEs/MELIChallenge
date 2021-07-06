package com.crisav2.challengemeli.list.presenter

import android.content.Context
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.list.ProductList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder
import android.util.Log
import com.crisav2.challengemeli.repository.SearchAPI
import com.crisav2.challengemeli.repository.model.asSearchResult
import com.crisav2.core.data.Product
import com.crisav2.core.usecase.Transformation

class ProductListPresenter(
    private val view: ProductList.View,
    override val messageManager: IMessageManager,
    private val searchAPI: SearchAPI,
    private val transformation: Transformation
): ProductList.Presenter {

    private var keyword: String = ""
    private var disposables = CompositeDisposable()

    override fun initialize(keyword: String) {
        this.keyword = keyword
        startSearch()
    }

    override fun evaluateKeyword() {
        if(keyword.isEmpty()){
            showError()
        }else{
            startSearch()
        }
    }

    override fun destroy() {
        disposables.dispose()
        disposables.clear()
    }

    override fun validateIdAndNavigate(id: String) {
        if(id.isNotEmpty()){
            view.goToProductDetail(id)
        }
    }

    private fun loadProducts() {
        val searchDisposable = searchAPI.getProducts(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val resultModel = it.asSearchResult()
                view.showLoading(false)
                when(resultModel.results?.isNotEmpty()){
                    true -> {
                        validateThumbnailURL(resultModel.results!!)
                        view.showList(resultModel.results!!)
                    }
                    false-> view.showError(messageManager.errorEmptyResults)
                }
            },{
                view.showLoading(false)
                view.showError(it.localizedMessage)
            })
        disposables.add(searchDisposable)
    }

    private fun validateThumbnailURL(results: List<Product>) {
        results.forEach {
            if(it.secureThumbnail.isEmpty()){
                it.secureThumbnail = transformation.validateSecureURLAndTransform(it.thumbnail)
            }
        }
    }

    private fun startSearch() {
        view.showLoading(true)
        view.setTitle("${messageManager.titleProductListScreen} $keyword")
        loadProducts()
    }

    private fun showError() {
        view.showError(messageManager.errorEmptySearch)
    }


}