package br.com.carina.grocery_plan.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchases")
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val marketName: String,
    val price: String
)
