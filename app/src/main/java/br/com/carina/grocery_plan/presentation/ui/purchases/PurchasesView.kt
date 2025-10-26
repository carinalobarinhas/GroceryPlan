package br.com.carina.grocery_plan.presentation.ui.purchases

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import br.com.carina.grocery_plan.R
import br.com.carina.grocery_plan.data.state.GroceryPlanEvent
import br.com.carina.grocery_plan.data.state.GroceryPlanState
import br.com.carina.grocery_plan.data.model.Purchase
import br.com.carina.grocery_plan.presentation.design.components.CustomToolbar
import br.com.carina.grocery_plan.presentation.design.components.button.PrimaryButton
import br.com.carina.grocery_plan.presentation.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.presentation.design.foundation.LSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.XLSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.XXXLSpacing

@Composable
fun PurchasesView(
    state: GroceryPlanState,
    event: (GroceryPlanEvent) -> Unit,
) {
    LaunchedEffect(Unit) {
        event(GroceryPlanEvent.GetPurchases)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.purchases.isEmpty()) {
            Text(
                text = stringResource(R.string.purchases_view_empty),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = XXXLSpacing)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
        Column {
            CustomToolbar(
                title = stringResource(R.string.purchases_view_title),
                hasBackButton = false
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(LSpacing),
                verticalArrangement = Arrangement.spacedBy(LSpacing)
            ) {
                items(state.purchases) { purchase ->
                    PurchasesItem(
                        purchase = purchase,
                        onDelete = {
                            event(GroceryPlanEvent.OpenDeletePurchaseDialog(it))
                        },
                        onAddPrice = {
                            event(GroceryPlanEvent.OpenAddPriceDialog(it))
                        },
                        onClick = {
                            event(GroceryPlanEvent.SelectedPurchase(it))
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
            text = stringResource(R.string.purchases_view_create)
        ) {
            event(GroceryPlanEvent.OpenNewPurchaseDialog)
        }
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
            state = GroceryPlanState(
                purchases = remember { list }
            ),
            event = {}
        )
    }
}