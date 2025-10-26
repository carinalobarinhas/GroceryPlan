package br.com.carina.grocery_plan.data.state

import br.com.carina.grocery_plan.data.model.Product
import br.com.carina.grocery_plan.data.model.SavedPrice

sealed class GroceryPlanEvent {
    data object OpenNewPurchaseDialog : GroceryPlanEvent()
    data class OpenAddPriceDialog(val savedPrice: SavedPrice) : GroceryPlanEvent()
    data class OpenDeletePurchaseDialog(val purchaseId: Int) : GroceryPlanEvent()
    data class OpenNewProductDialog(val purchaseId: Int) : GroceryPlanEvent()
    data class OpenDeleteProductDialog(val productId: Int) : GroceryPlanEvent()
    data class SelectedPurchase(val purchaseId: Int) : GroceryPlanEvent()
    data object BackPress : GroceryPlanEvent()
    data object GetPurchases : GroceryPlanEvent()
    data class GetProducts(val purchaseId: Int) : GroceryPlanEvent()
    data class CreatePurchase(val purchaseName: String) : GroceryPlanEvent()
    data class UpdatePurchasePrice(val purchaseId: Int, val price: String) : GroceryPlanEvent()
    data class DeletePurchase(val purchaseId: Int) : GroceryPlanEvent()
    data class AddProduct(val purchaseId: Int, val product: Product) : GroceryPlanEvent()
    data class UpdateProductQuantity(val productId: Int, val quantity: Int) : GroceryPlanEvent()
    data class DeleteProduct(val productId: Int) : GroceryPlanEvent()
}