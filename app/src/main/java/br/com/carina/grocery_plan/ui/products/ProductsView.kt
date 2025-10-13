package br.com.carina.grocery_plan.ui.products

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
import br.com.carina.grocery_plan.design.components.CustomToolbar
import br.com.carina.grocery_plan.design.components.button.PrimaryButton
import br.com.carina.grocery_plan.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.design.foundation.LSpacing
import br.com.carina.grocery_plan.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.design.foundation.XLSpacing
import br.com.carina.grocery_plan.design.foundation.XXXLSpacing

@Composable
fun ProductsView(
    products: List<Product> = emptyList(),
    onBackClick: () -> Unit = {},
) {

    val newProductDialogState = remember { DialogState<Unit>() }
    val deleteProductDialogState = remember { DialogState<Unit>() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if(products.isEmpty()){
            Text(
                text = "Você ainda não tem produtos cadastradas, crie um novo para começar",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = XXXLSpacing)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
        Column {
            CustomToolbar(
                title = "Lista de Produtos",
                hasBackButton = true,
                onBackClick = onBackClick
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(LSpacing),
                verticalArrangement = Arrangement.spacedBy(LSpacing)
            ) {
                items(products) { product ->
                    ProductsItem(
                        product = product,
                        onChangeQuantity = {},
                        onDelete = {
                            deleteProductDialogState.showDialog(Unit)
                        },
                    )
                }
            }
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(XLSpacing),
            text = "Criar Novo Item"
        ) {
            newProductDialogState.showDialog(Unit)
        }

        NewProductDialog(
            state = newProductDialogState,
            onConfirm = { name, quantity -> },
            onDismiss = {}
        )

        DeleteProductDialog(
            state = deleteProductDialogState,
            onConfirm = { },
            onDismiss = {}
        )
    }
}

@LightThemedPreview
@DarkThemedPreview
@Composable
fun PurchasesViewPreview() {
    val list = mutableListOf<Product>()
    repeat(20) { index ->
        list.add(
            Product(
                name = "Nome do Produto $index",
                quantity = index % 2 + 1
            )
        )
    }

    GroceryPlanTheme {
        ProductsView(
            products = list
        )
    }
}