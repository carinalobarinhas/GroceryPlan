package br.com.carina.grocery_plan.ui.products

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import br.com.carina.grocery_plan.R
import br.com.carina.grocery_plan.data.Product
import br.com.carina.grocery_plan.data.Purchase
import br.com.carina.grocery_plan.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.design.foundation.LSpacing
import br.com.carina.grocery_plan.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.design.foundation.MSpacing
import br.com.carina.grocery_plan.design.foundation.SSpacing
import br.com.carina.grocery_plan.design.foundation.XLSpacing

@Composable
fun ProductsItem(
    product: Product,
    onChangeQuantity: (Int) -> Unit,
    onDelete: () -> Unit,
) {
    var quantity by remember { mutableIntStateOf(0) }

    LaunchedEffect(product.quantity) {
        quantity = product.quantity
    }

    Card{
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
            Text(
                modifier = Modifier,
                text = product.name,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            if(quantity == 1) {
                IconButton(
                    onClick = {
                        onDelete()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Excluir produto"
                    )
                }
            } else {
                IconButton(
                    onClick = {
                        quantity = quantity - 1
                        onChangeQuantity(quantity)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_decrement),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Diminuir quantidade"
                    )
                }
            }
            Spacer(modifier = Modifier.padding(horizontal = SSpacing))
            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.padding(horizontal = SSpacing))
            IconButton(
                onClick = {
                    quantity = quantity + 1
                    onChangeQuantity(quantity)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_increment),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Aumentar quantidade"
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
                ProductsItem(
                    Product(
                        name = "Produto $index",
                        quantity = 2,
                    ),
                    onDelete = {},
                    onChangeQuantity = {}
                )
            }
        }
    }
}