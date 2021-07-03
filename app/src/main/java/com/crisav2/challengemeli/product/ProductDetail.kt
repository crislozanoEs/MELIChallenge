package com.crisav2.challengemeli.product

import com.crisav2.core.data.Product

interface ProductDetail {
  interface View{
    fun showLoading(isShowing: Boolean)
    fun showError(error: String)
    fun setProductInView(product: Product)
  }

  interface Presenter{
    fun initialize(productID: String?)
    fun destroy()
  }

}