package com.crisav2.core.usecase


class Validators {

    fun validateKeyword(keyword: String): Boolean{
        return keyword.isNotEmpty()
    }
}