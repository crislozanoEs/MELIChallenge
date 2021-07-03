package com.crisav2.challengemeli.repository.model

import java.util.*

data class SellerNetwork(
    val id: String = "",
    val permalink: String = "",
    val registrationDate: Date? = null,
    val isCarDealer: Boolean = false
)
