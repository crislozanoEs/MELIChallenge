package com.crisav2.core.usecase

import com.crisav2.core.data.Product


/**
 * Clase para validar objetos o string, segun la logica de negocio
 */
open class Validators {

    fun validateKeyword(keyword: String): Boolean{
        return keyword.isNotEmpty()
    }

    fun validateProduct(product: Product): Boolean {
        return product.id.isNotEmpty()
    }
}