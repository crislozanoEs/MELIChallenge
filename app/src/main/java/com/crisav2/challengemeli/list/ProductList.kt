package com.crisav2.challengemeli.list

import com.crisav2.core.data.Product

interface ProductList {

    interface View{
        fun showError(error: String)
        fun showLoading(isShowing: Boolean)
        fun setTitle(keyword: String)
        fun showList(productList: List<Product>)
        fun goToProductDetail(id: String)
    }

    interface Presenter{
        fun initialize(keyword: String)
        fun evaluateKeyword()
        fun destroy()
        fun validateIdAndNavigate(id: String)
    }
}