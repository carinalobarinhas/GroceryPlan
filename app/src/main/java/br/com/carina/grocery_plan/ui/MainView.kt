package br.com.carina.grocery_plan.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.carina.grocery_plan.data.Product
import br.com.carina.grocery_plan.data.Purchase
import br.com.carina.grocery_plan.ui.products.ProductsView
import br.com.carina.grocery_plan.ui.purchases.PurchasesView

@Composable
fun MainView() {
    val navController = rememberNavController()
    var selectedProducts by remember { mutableStateOf<List<Product>>(emptyList()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(navController = navController, startDestination = "Purchases") {
            composable("Purchases") {
                PurchasesView(
                    purchases = listOf(
                        Purchase(
                            date = "01/01/2023",
                            marketName = "Nome do Mercado",
                            price = "",
                            products = listOf(
                                Product("Banana", 2 ),
                                Product("Maça", 2 ),
                                Product("Uva", 1 ),
                            )
                        )
                    ),
                    openProducts = { products ->
                        selectedProducts = products
                        navController.navigate("Products")
                    }
                )
            }
            composable("Products") {
                ProductsView(
                    products = selectedProducts,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}