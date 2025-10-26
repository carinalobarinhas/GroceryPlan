package br.com.carina.grocery_plan.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.carina.grocery_plan.data.state.GroceryPlanEffect
import br.com.carina.grocery_plan.data.state.GroceryPlanEffect.ShowAddPriceDialog
import br.com.carina.grocery_plan.data.state.GroceryPlanEffect.ShowDeleteCartDialog
import br.com.carina.grocery_plan.data.state.GroceryPlanEffect.ShowDeleteProductDialog
import br.com.carina.grocery_plan.data.state.GroceryPlanEvent
import br.com.carina.grocery_plan.data.state.GroceryPlanState
import br.com.carina.grocery_plan.data.model.Product
import br.com.carina.grocery_plan.data.model.Purchase
import br.com.carina.grocery_plan.data.room.repository.PurchaseRepository
import br.com.carina.grocery_plan.domain.extension.formatDate
import br.com.carina.grocery_plan.domain.scaffold.ViewModelScaffold
import br.com.carina.grocery_plan.domain.scaffold.viewModelScaffold
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class GroceryPlanViewModel @Inject constructor(
    val repository: PurchaseRepository
) : ViewModel(),
    ViewModelScaffold<GroceryPlanState, GroceryPlanEvent, GroceryPlanEffect> by viewModelScaffold(
        initialState = GroceryPlanState()
    ) {
    override fun handleEvent(event: GroceryPlanEvent) {
        when (event) {
            is GroceryPlanEvent.OpenAddPriceDialog -> viewModelScope.emitSideEffect(
                ShowAddPriceDialog(event.savedPrice)
            )

            is GroceryPlanEvent.OpenDeletePurchaseDialog -> viewModelScope.emitSideEffect(
                ShowDeleteCartDialog(event.purchaseId)
            )

            is GroceryPlanEvent.OpenDeleteProductDialog -> viewModelScope.emitSideEffect(
                ShowDeleteProductDialog(event.productId)
            )

            is GroceryPlanEvent.OpenNewProductDialog -> viewModelScope.emitSideEffect(
                GroceryPlanEffect.ShowNewProductDialog(event.purchaseId)
            )

            is GroceryPlanEvent.OpenNewPurchaseDialog -> viewModelScope.emitSideEffect(
                GroceryPlanEffect.ShowNewPurchaseDialog
            )

            is GroceryPlanEvent.GetPurchases -> getPurchases()
            is GroceryPlanEvent.GetProducts -> getProducts(event.purchaseId)
            is GroceryPlanEvent.AddProduct -> addProduct(event.purchaseId, event.product)
            is GroceryPlanEvent.CreatePurchase -> createPurchase(event.purchaseName)
            is GroceryPlanEvent.DeleteProduct -> deleteProduct(event.productId)
            is GroceryPlanEvent.DeletePurchase -> deletePurchase(event.purchaseId)
            is GroceryPlanEvent.UpdateProductQuantity -> updateProductQuantity(
                event.productId,
                event.quantity
            )

            is GroceryPlanEvent.UpdatePurchasePrice -> updatePurchasePrice(
                event.purchaseId,
                event.price
            )

            is GroceryPlanEvent.BackPress -> viewModelScope.emitSideEffect(GroceryPlanEffect.BackPress)
            is GroceryPlanEvent.SelectedPurchase -> selectPurchase(event.purchaseId)
        }
    }

    private val handler = CoroutineExceptionHandler { _, throwable ->

    }

    fun selectPurchase(purchaseId: Int) = viewModelScope.launch(handler) {
        updateProducts(purchaseId)
        viewModelScope.emitSideEffect(GroceryPlanEffect.OpenProducts)
    }

    fun getPurchases() = viewModelScope.launch(handler) {
        updatePurchases()
    }

    private suspend fun updatePurchases() {
        val purchases = repository.getAllPurchases()
        updateViewState { it.copy(purchases = purchases) }
    }

    fun getProducts(purchaseId: Int) = viewModelScope.launch(handler) {
        updateProducts(purchaseId)
    }

    private suspend fun updateProducts(purchaseId: Int) {
        val products = repository.getProducts(purchaseId)
        updateViewState {
            it.copy(
                products = products,
                selectedPurchaseId = purchaseId
            )
        }
    }

    fun addProduct(purchaseId: Int, product: Product) = viewModelScope.launch(handler) {
        repository.insertProduct(purchaseId, product)
        updateProducts(viewState.value.selectedPurchaseId)
    }

    fun createPurchase(purchaseName: String) = viewModelScope.launch(handler) {
        val purchase = Purchase(
            date = Date().formatDate(),
            marketName = purchaseName
        )
        repository.insertPurchase(purchase)
        updatePurchases()
    }

    fun deleteProduct(productId: Int) = viewModelScope.launch(handler) {
        repository.deleteProduct(productId)
        updateProducts(viewState.value.selectedPurchaseId)
    }

    fun deletePurchase(purchaseId: Int) = viewModelScope.launch(handler) {
        repository.deletePurchase(purchaseId)
        updatePurchases()
    }

    fun updateProductQuantity(productId: Int, quantity: Int) = viewModelScope.launch(handler) {
        repository.updateProductQuantity(productId, quantity)
        updateProducts(viewState.value.selectedPurchaseId)
    }

    fun updatePurchasePrice(purchaseId: Int, price: String) = viewModelScope.launch(handler) {
        repository.updatePurchasePrice(purchaseId, price)
        updatePurchases()
    }
}