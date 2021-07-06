package com.crisav2.challengemeli.list

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.common.MessageManager
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
        val messageManager: IMessageManager
        fun initialize(keyword: String)
        fun evaluateKeyword()
        fun destroy()
        fun validateIdAndNavigate(id: String)
    }
}