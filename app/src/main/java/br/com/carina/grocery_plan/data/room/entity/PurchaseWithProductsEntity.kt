package br.com.carina.grocery_plan.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PurchaseWithProductsEntity(
    @Embedded val purchase: PurchaseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "purchaseId"
    )
    val products: List<ProductEntity>
)
