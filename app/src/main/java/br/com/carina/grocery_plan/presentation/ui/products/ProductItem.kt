package br.com.carina.grocery_plan.presentation.ui.products

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.carina.grocery_plan.R
import br.com.carina.grocery_plan.data.model.Product
import br.com.carina.grocery_plan.presentation.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.presentation.design.foundation.LSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.MSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.SSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.XLSpacing

@Composable
fun ProductsItem(
    product: Product,
    onChangeQuantity: (Int, Int) -> Unit,
    onDelete: (Int) -> Unit,
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
                        onDelete(product.id)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = stringResource(R.string.product_item_delete)
                    )
                }
            } else {
                IconButton(
                    onClick = {
                        quantity = quantity - 1
                        onChangeQuantity(product.id, quantity)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_decrement),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = stringResource(R.string.product_item_reduce)
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
                    onChangeQuantity(product.id, quantity)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_increment),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.product_item_increase)
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
                    onChangeQuantity = {_, _ ->}
                )
            }
        }
    }
}