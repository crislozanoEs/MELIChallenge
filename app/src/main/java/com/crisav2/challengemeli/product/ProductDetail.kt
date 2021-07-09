package com.crisav2.challengemeli.product

import com.crisav2.core.data.Product

interface ProductDetail {
  interface View{

    /**
     * Mostrar "cargando"
     * @param isShowing mostrar o no
     */

    fun showLoading(isShowing: Boolean)

    /**
     * Mostrar error
     * @param error cadena del error
     * @param withButton habilitar el boton de retry
     */

    fun setErrorView(error: String, withButton: Boolean = true)

    /**
     * Mostrar product
     * @param product producto seleccionado
     */

    fun setProductInView()

    /**
     * Mostrar datos product
     * @param product producto seleccionado
     */

    fun setDataInView(product: ProductDetail.ProductInView)

    /**
     * Agregar titulo a boton
     * @param titleTryAgainProductDetail titulo
     */

    fun setUpTryAgainButtonTitle(titleTryAgainProductDetail: String)
  }

  interface Presenter{

    /**
     * Inicializa presenter
     * @param productID id del producto seleccionado
     */

    fun initialize(productID: String?)

    /**
     * Limpia los objetos
     */

    fun destroy()

    /**
     * Vuelve a intentar la busqueda del producto
     */

    fun tryDetailAgain()
  }

  data class ProductInView(
    val title: String,
    val thumbnail: String,
    val price: String,
    val quantity: String,
    val condition: String
  )
}