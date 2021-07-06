package com.crisav2.core.usecase

class Transformation {
    private val secureProtocol = "https"
    private val unsecureProtocol = "http"

    fun validateSecureURLAndTransform(url: String): String{
        return if(!url.contains(secureProtocol)){
            "$secureProtocol${url.substring(4)}"
        }else{
            url
        }
    }
}