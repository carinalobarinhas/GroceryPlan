package br.com.carina.grocery_plan.data.room.repository

import br.com.carina.grocery_plan.data.model.Product
import br.com.carina.grocery_plan.data.model.Purchase
import br.com.carina.grocery_plan.data.room.dao.PurchaseDao
import br.com.carina.grocery_plan.data.room.mapper.toDomain
import br.com.carina.grocery_plan.data.room.mapper.toEntity
import br.com.carina.grocery_plan.data.room.mapper.toEntityPair
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val dao: PurchaseDao
) : PurchaseRepository {
    override suspend fun getAllPurchases(): List<Purchase> =
        dao.getAllPurchasesWithProducts().map { it.toDomain() }

    override suspend fun getPurchase(purchaseId: Int): Purchase? =
        dao.getPurchaseWithProducts(purchaseId)?.toDomain()

    override suspend fun getProducts(purchaseId: Int): List<Product> =
        dao.getProductsByPurchase(purchaseId).map { it.toDomain() }

    override suspend fun insertPurchase(purchase: Purchase) {
        val (purchaseEntity, productEntities) = purchase.toEntityPair()
        dao.insertPurchaseWithProducts(purchaseEntity, productEntities)
    }

    override suspend fun updatePurchasePrice(purchaseId: Int, price: String) {
        dao.updatePurchasePrice(purchaseId, price)
    }

    override suspend fun deletePurchase(purchaseId: Int) {
        dao.deletePurchaseById(purchaseId)
    }

    override suspend fun insertProduct(purchaseId: Int, product: Product) {
        dao.insertProductEntity(product.toEntity(purchaseId))
    }

    override suspend fun updateProductQuantity(productId: Int, quantity: Int) {
        dao.updateProductQuantity(productId, quantity)
    }

    override suspend fun deleteProduct(productId: Int) {
        dao.deleteProductById(productId)
    }
}