package com.senaaksoy.cupcakeapp.data

data class CupcakeUiState(
    val flavor: String = "",
    val quantity: Int = 0,
    val subtotal: Int = 0,
    val pickupDate: String = "",
    val dateList: MutableList<String> = mutableListOf()
)
