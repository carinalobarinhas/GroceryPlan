package br.com.carina.grocery_plan.data.model

data class Purchase(
    val id: Int = 0,
    val date: String,
    val marketName: String,
    val price: String = "",
    val products: List<Product> = emptyList()
)
