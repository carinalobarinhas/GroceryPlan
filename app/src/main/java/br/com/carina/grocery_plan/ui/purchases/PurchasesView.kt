package br.com.carina.grocery_plan.ui.purchases

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import br.com.carina.grocery_plan.data.DialogState
import br.com.carina.grocery_plan.data.Product
import br.com.carina.grocery_plan.data.Purchase
import br.com.carina.grocery_plan.design.components.CustomToolbar
import br.com.carina.grocery_plan.design.components.button.PrimaryButton
import br.com.carina.grocery_plan.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.design.foundation.LSpacing
import br.com.carina.grocery_plan.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.design.foundation.XLSpacing
import br.com.carina.grocery_plan.design.foundation.XXXLSpacing

@Composable
fun PurchasesView(
    purchases: List<Purchase> = emptyList(),
    openProducts: (List<Product>) -> Unit = { },
) {
    val addPriceDialogState = remember { DialogState<Unit>() }
    val deleteCartDialogState = remember { DialogState<Unit>() }
    val newPurchaseDialogState = remember { DialogState<Unit>() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (purchases.isEmpty()) {
            Text(
                text = "Você ainda não tem compras cadastradas, crie um novo para começar",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = XXXLSpacing)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
        Column {
            CustomToolbar(
                title = "Grocery Plan",
                hasBackButton = false
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(LSpacing),
                verticalArrangement = Arrangement.spacedBy(LSpacing)
            ) {
                items(purchases) { purchase ->
                    PurchasesItem(
                        purchase = purchase,
                        onDelete = {
                            deleteCartDialogState.showDialog(Unit)
                        },
                        onAddPrice = {
                            addPriceDialogState.showDialog(Unit)
                        },
                        onClick = {
                            openProducts(purchase.products)
                        }
                    )
                }
            }
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(XLSpacing),
            text = "Criar Carrinho"
        ) {
            newPurchaseDialogState.showDialog(Unit)
        }

        AddPriceDialog(
            state = addPriceDialogState,
            onConfirm = { },
            onDismiss = {}
        )

        DeleteCartDialog(
            state = deleteCartDialogState,
            onConfirm = { },
            onDismiss = {}
        )

        NewPurchaseDialog(
            state = newPurchaseDialogState,
            onConfirm = { },
            onDismiss = {}
        )
    }
}

@LightThemedPreview
@DarkThemedPreview
@Composable
fun PurchasesViewPreview() {
    val list = mutableListOf<Purchase>()
    repeat(20) {
        list.add(
            Purchase(
                date = "Compra do dia 10/10",
                marketName = "Nome do Mercado 1",
                price = "R$ 600,00",
                products = emptyList()
            )
        )
    }

    GroceryPlanTheme {
        PurchasesView(
            purchases = list
        )
    }
}