package br.com.carina.grocery_plan.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.carina.grocery_plan.data.room.dao.PurchaseDao
import br.com.carina.grocery_plan.data.room.entity.ProductEntity
import br.com.carina.grocery_plan.data.room.entity.PurchaseEntity

@Database(
    entities = [PurchaseEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun purchaseDao(): PurchaseDao
}