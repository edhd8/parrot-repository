package io.parrotsoftware.pos.common.request

data class OrderRequest(
    var totalPrice: Double,
    var products: ArrayList<String>,
    var quantities: ArrayList<Int>,
    var subtotals: ArrayList<Double>
)