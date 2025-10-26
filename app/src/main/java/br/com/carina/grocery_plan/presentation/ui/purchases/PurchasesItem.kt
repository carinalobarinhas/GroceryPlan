package br.com.carina.grocery_plan.presentation.ui.purchases

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import br.com.carina.grocery_plan.R
import br.com.carina.grocery_plan.data.model.Purchase
import br.com.carina.grocery_plan.data.model.SavedPrice
import br.com.carina.grocery_plan.presentation.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.presentation.design.foundation.LSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.MSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.SSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.XLSpacing

@Composable
fun PurchasesItem(
    purchase: Purchase,
    onDelete: (Int) -> Unit,
    onAddPrice: (SavedPrice) -> Unit,
    onClick: (Int) -> Unit = {}
) {
    val price = purchase.price
    Card(
        modifier = Modifier.clickable(onClick = {
            onClick(purchase.id)
        })
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = XLSpacing,
                    top = MSpacing,
                    bottom = MSpacing
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SSpacing)
        ) {
            Column {
                Text(
                    text = purchase.date,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = purchase.marketName,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable {
                    val currentPrice = if (price == "") "" else price
                    onAddPrice(SavedPrice(purchase.id, currentPrice))
                },
                text = if (price == "") stringResource(R.string.purchases_item_add_label) else price,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = TextDecoration.Underline
                ),
                fontWeight = FontWeight.Bold,
                color = if (price == "") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
            IconButton(
                onClick = {
                    onDelete(purchase.id)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.purchases_item_delete)
                )
            }
        }
    }
}

@LightThemedPreview
@DarkThemedPreview
@Composable
fun PurchasesItemPreview() {
    GroceryPlanTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(LSpacing),
            verticalArrangement = Arrangement.spacedBy(LSpacing)
        ) {
            items(2) { index ->
                PurchasesItem(
                    Purchase(
                        date = "Compra do dia 10/10",
                        marketName = "Nome do Mercado",
                        price = if (index % 2 == 0) "R$ 600,00" else "",
                        products = emptyList()
                    ),
                    onDelete = {},
                    onAddPrice = {}
                )
            }
        }
    }
}