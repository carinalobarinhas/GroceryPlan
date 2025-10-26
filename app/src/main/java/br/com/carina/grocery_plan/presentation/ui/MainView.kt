package br.com.carina.grocery_plan.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.carina.grocery_plan.data.model.SavedPrice
import br.com.carina.grocery_plan.data.state.DialogState
import br.com.carina.grocery_plan.data.state.GroceryPlanEffect
import br.com.carina.grocery_plan.data.state.GroceryPlanEvent
import br.com.carina.grocery_plan.data.state.GroceryPlanState
import br.com.carina.grocery_plan.presentation.ui.products.DeleteProductDialog
import br.com.carina.grocery_plan.presentation.ui.products.NewProductDialog
import br.com.carina.grocery_plan.presentation.ui.products.ProductsView
import br.com.carina.grocery_plan.presentation.ui.purchases.AddPriceDialog
import br.com.carina.grocery_plan.presentation.ui.purchases.DeleteCartDialog
import br.com.carina.grocery_plan.presentation.ui.purchases.NewPurchaseDialog
import br.com.carina.grocery_plan.presentation.ui.purchases.PurchasesView
import kotlinx.coroutines.flow.Flow

@Composable
fun MainView(
    state: GroceryPlanState,
    event: (GroceryPlanEvent) -> Unit,
    effect: Flow<GroceryPlanEffect>
) {
    val navController = rememberNavController()
    val newProductDialogState = remember { DialogState<Int>() }
    val deleteProductDialogState = remember { DialogState<Int>() }
    val addPriceDialogState = remember { DialogState<SavedPrice>() }
    val deleteCartDialogState = remember { DialogState<Int>() }
    val newPurchaseDialogState = remember { DialogState<Unit>() }

    LaunchedEffect(effect) {
        effect.collect { groceryPlanEffect ->
            when (groceryPlanEffect) {
                is GroceryPlanEffect.ShowAddPriceDialog -> addPriceDialogState.showDialog(
                    groceryPlanEffect.savedPrice
                )

                is GroceryPlanEffect.ShowDeleteCartDialog -> deleteCartDialogState.showDialog(
                    groceryPlanEffect.id
                )

                is GroceryPlanEffect.ShowDeleteProductDialog -> deleteProductDialogState.showDialog(
                    groceryPlanEffect.id
                )

                is GroceryPlanEffect.ShowNewProductDialog -> newProductDialogState.showDialog(
                    groceryPlanEffect.id
                )

                is GroceryPlanEffect.ShowNewPurchaseDialog -> newPurchaseDialogState.showDialog(Unit)
                is GroceryPlanEffect.OpenProducts -> navController.navigate("Products")
                is GroceryPlanEffect.BackPress -> navController.popBackStack()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(navController = navController, startDestination = "Purchases") {
            composable("Purchases") {
                PurchasesView(
                    state = state,
                    event = event
                )
            }
            composable("Products") {
                ProductsView(
                    state = state,
                    event = event
                )
            }
        }
    }

    NewProductDialog(
        state = newProductDialogState,
        onConfirm = { id, product ->
            event(GroceryPlanEvent.AddProduct(purchaseId = id, product = product))
        },
        onDismiss = {}
    )

    DeleteProductDialog(
        state = deleteProductDialogState,
        onConfirm = {
            event(GroceryPlanEvent.DeleteProduct(it))
        },
        onDismiss = {}
    )

    AddPriceDialog(
        state = addPriceDialogState,
        onConfirm = { id, price ->
            event(GroceryPlanEvent.UpdatePurchasePrice(purchaseId = id, price = price))
        },
        onDismiss = {}
    )

    DeleteCartDialog(
        state = deleteCartDialogState,
        onConfirm = {
            event(GroceryPlanEvent.DeletePurchase(it))
        },
        onDismiss = {}
    )

    NewPurchaseDialog(
        state = newPurchaseDialogState,
        onConfirm = {
            event(GroceryPlanEvent.CreatePurchase(it))
        },
        onDismiss = {}
    )
}