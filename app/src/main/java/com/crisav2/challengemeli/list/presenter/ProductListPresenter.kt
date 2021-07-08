package com.crisav2.challengemeli.list.presenter

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.list.ProductList
import io.reactivex.disposables.CompositeDisposable
import com.crisav2.challengemeli.repository.SearchAPI
import com.crisav2.challengemeli.repository.model.asSearchResult
import com.crisav2.core.data.Product
import com.crisav2.core.usecase.Transformation
import io.reactivex.Scheduler

/**
 * ProductListPresenter - Presenter
 *
 * Presenter que presenta la lista de los productos que el usuario buscó.
 * View: ProductList.View
 */

class ProductListPresenter(
    private val view: ProductList.View,
    override val messageManager: IMessageManager,
    private val searchAPI: SearchAPI,
    override val transformation: Transformation,
    private val subscribeOn: Scheduler,
    private val observeOn: Scheduler
): ProductList.Presenter {

    private var keyword: String = ""
    private var disposables = CompositeDisposable()

    // Public methods section
    override fun initialize(keyword: String?) {
        keyword?.let {
            this.keyword = it
            startSearch()
        }?: run{
            showError()
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

    // Private methods section
    private fun loadProducts() {
        val searchDisposable = searchAPI.getProducts(keyword)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .doFinally{ view.showLoading(false)}
            .subscribe({
                val resultModel = it.asSearchResult()
                when(resultModel.results?.isNotEmpty()){
                    true -> {
                        validateThumbnailURL(resultModel.results!!)
                        view.showList(resultModel.results!!)
                    }
                    false-> view.showError(messageManager.errorEmptyResults)
                }
            },{
                view.showError(it.localizedMessage ?: messageManager.errorEmptySearch)
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
        view.showLoading(false)
        view.showError(messageManager.errorEmptySearch)
    }
}