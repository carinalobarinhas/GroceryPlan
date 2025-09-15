package br.com.carina.grocery_plan.ui.purchases

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.carina.grocery_plan.data.Purchase
import br.com.carina.grocery_plan.design.components.CustomToolbar
import br.com.carina.grocery_plan.design.components.button.PrimaryButton
import br.com.carina.grocery_plan.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.design.foundation.LSpacing
import br.com.carina.grocery_plan.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.design.foundation.MSpacing
import br.com.carina.grocery_plan.design.foundation.SSpacing
import br.com.carina.grocery_plan.design.foundation.XLSpacing

@Composable
fun PurchasesView(
    purchases: List<Purchase> = emptyList(),
    onDelete: (Purchase) -> Unit
) {
    Box {
        Column {
            CustomToolbar(
                title = "Grocery Plan",
                hasBackButton = false
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(LSpacing),
                verticalArrangement = Arrangement.spacedBy(LSpacing)
            ) {
                items(purchases) { purchase ->
                    PurchasesItem(
                        purchase = purchase,
                        onDelete = onDelete
                    )
                }
            }
        }
        PrimaryButton(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(XLSpacing),
            text = "Criar Carrinho"
        ) { }
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
            )
        )
    }

    GroceryPlanTheme {
        PurchasesView(
            purchases = list,
            onDelete = {}
        )
    }
}