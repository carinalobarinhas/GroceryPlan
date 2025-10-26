package br.com.carina.grocery_plan.data.state

import br.com.carina.grocery_plan.data.model.Product
import br.com.carina.grocery_plan.data.model.Purchase

data class GroceryPlanState(
    val purchases: List<Purchase> = emptyList(),
    val products: List<Product> = emptyList(),
    val selectedPurchaseId: Int = 0
)