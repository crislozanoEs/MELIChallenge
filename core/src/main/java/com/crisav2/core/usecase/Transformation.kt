package com.crisav2.core.usecase

import java.text.NumberFormat
import java.util.*

/**
 * Clase que transforma cadenas, valores numericos, etc.
 */

open class Transformation {
    private val secureProtocol = "https"
    private val currencyInstance = "COP"
    private val format = NumberFormat.getCurrencyInstance()

    init{
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance(Locale("es","es_CO"))
    }

    // Valida si una URL es segura o no. Si no lo es, la vuelve segura http -> https
    fun validateSecureURLAndTransform(url: String): String{
        return if(!url.contains(secureProtocol)){
            "$secureProtocol${url.substring(4)}"
        }else{
            url
        }
    }

    // Transforma un string en un string de precio
    fun getPrettyPrice(price: String): String {
        val priceInNumber = Integer.parseInt(price)
        return format.format(priceInNumber)
    }
}