package br.com.carina.grocery_plan.data.room.mapper

import br.com.carina.grocery_plan.data.model.Product
import br.com.carina.grocery_plan.data.model.Purchase
import br.com.carina.grocery_plan.data.room.entity.ProductEntity
import br.com.carina.grocery_plan.data.room.entity.PurchaseEntity
import br.com.carina.grocery_plan.data.room.entity.PurchaseWithProductsEntity

fun Product.toEntity(purchaseId: Int) = ProductEntity(
    id = if (id == 0) 0 else id,
    name = name,
    quantity = quantity,
    purchaseId = purchaseId
)

fun ProductEntity.toDomain() = Product(
    id = id,
    name = name,
    quantity = quantity
)

fun Purchase.toEntity() = PurchaseEntity(
    id = if (id == 0) 0 else id,
    date = date,
    marketName = marketName,
    price = price
)

fun PurchaseWithProductsEntity.toDomain() = Purchase(
    id = purchase.id,
    date = purchase.date,
    marketName = purchase.marketName,
    price = purchase.price,
    products = products.map { it.toDomain() }
)

fun Purchase.toEntityPair(): Pair<PurchaseEntity, List<ProductEntity>> {
    val pEntity = toEntity()
    val productEntities = products.map { it.toEntity(purchaseId = id) }
    return pEntity to productEntities
}