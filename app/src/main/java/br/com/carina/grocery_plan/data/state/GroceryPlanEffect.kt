package br.com.carina.grocery_plan.data.state

import br.com.carina.grocery_plan.data.model.SavedPrice

sealed class GroceryPlanEffect {
    data object ShowNewPurchaseDialog : GroceryPlanEffect()
    data class ShowAddPriceDialog(val savedPrice: SavedPrice) : GroceryPlanEffect()
    data class ShowDeleteCartDialog(val id: Int) : GroceryPlanEffect()
    data class ShowNewProductDialog(val id: Int) : GroceryPlanEffect()
    data class ShowDeleteProductDialog(val id: Int) : GroceryPlanEffect()
    data object OpenProducts : GroceryPlanEffect()
    data object BackPress : GroceryPlanEffect()
}