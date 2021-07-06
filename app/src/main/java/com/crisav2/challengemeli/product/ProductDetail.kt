package com.crisav2.challengemeli.product

import com.crisav2.core.data.Product

interface ProductDetail {
  interface View{
    fun showLoading(isShowing: Boolean)
    fun setErrorView(error: String, withButton: Boolean = true)
    fun setProductInView(product: Product)
    fun setUpTryAgainButtonTitle(titleTryAgainProductDetail: String)
  }

  interface Presenter{
    fun initialize(productID: String?)
    fun destroy()
    fun tryDetailAgain()
  }

}