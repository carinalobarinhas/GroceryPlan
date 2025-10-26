package br.com.carina.grocery_plan.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import br.com.carina.grocery_plan.data.room.entity.ProductEntity
import br.com.carina.grocery_plan.data.room.entity.PurchaseEntity
import br.com.carina.grocery_plan.data.room.entity.PurchaseWithProductsEntity

@Dao
interface PurchaseDao {
    /**
     * Retrieve all purchases along with their associated products.
     */
    @Transaction
    @Query("SELECT * FROM purchases WHERE id = :id")
    suspend fun getPurchaseWithProducts(id: Int): PurchaseWithProductsEntity?

    /**
     * Retrieve all purchases along with their associated products.
     */
    @Transaction
    @Query("SELECT * FROM purchases")
    suspend fun getAllPurchasesWithProducts(): List<PurchaseWithProductsEntity>

    /**
     * Retrieve all products associated with a specific purchase, ordered by name and ID.
     */
    @Query("SELECT * FROM products WHERE purchaseId = :purchaseId ORDER BY name ASC, id ASC")
    suspend fun getProductsByPurchase(purchaseId: Int): List<ProductEntity>

    /**
     * Retrieve all purchases along with their associated products.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchaseEntity(purchase: PurchaseEntity): Long

    /**
     * Insert multiple products into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductEntities(products: List<ProductEntity>)

    /**
     * Insert a purchase along with its associated products in a single transaction.
     */
    @Transaction
    suspend fun insertPurchaseWithProducts(purchase: PurchaseEntity, products: List<ProductEntity>) {
        val generatedPurchaseId = insertPurchaseEntity(purchase).toInt()
        val updatedProducts = products.map { it.copy(purchaseId = generatedPurchaseId) }
        insertProductEntities(updatedProducts)
    }

    /**
     * Update the price of a specific purchase.
     */
    @Query("UPDATE purchases SET price = :price WHERE id = :purchaseId")
    suspend fun updatePurchasePrice(purchaseId: Int, price: String)

    /**
     * Delete a specific purchase by its ID.
     */
    @Query("DELETE FROM purchases WHERE id = :purchaseId")
    suspend fun deletePurchaseById(purchaseId: Int)

    /**
     * Insert a single product into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductEntity(product: ProductEntity)

    /**
     * Update the quantity of a specific product.
     */
    @Query("UPDATE products SET quantity = :quantity WHERE id = :productId")
    suspend fun updateProductQuantity(productId: Int, quantity: Int)

    /**
     * Delete a specific product by its ID.
     */
    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProductById(productId: Int)
}