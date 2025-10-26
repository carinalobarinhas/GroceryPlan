package br.com.carina.grocery_plan.data.room.repository

import br.com.carina.grocery_plan.data.model.Product
import br.com.carina.grocery_plan.data.model.Purchase

interface PurchaseRepository {

    suspend fun getAllPurchases(): List<Purchase>
    suspend fun getPurchase(purchaseId: Int): Purchase?
    suspend fun getProducts(purchaseId: Int): List<Product>

    suspend fun insertPurchase(purchase: Purchase)

    suspend fun updatePurchasePrice(purchaseId: Int, price: String)

    suspend fun deletePurchase(purchaseId: Int)

    suspend fun insertProduct(purchaseId: Int, product: Product)

    suspend fun updateProductQuantity(productId: Int, quantity: Int)

    suspend fun deleteProduct(productId: Int)
}