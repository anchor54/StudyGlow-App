package com.example.studyglows.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "cart",
    primaryKeys = ["type", "item_id"]
)
data class Cart(
    @ColumnInfo(name = "item_id") val itemId: Long,
    val type: String
)

@Entity(
    tableName = "saved_items",
    primaryKeys = ["type", "item_id"]
)
data class SavedItem(
    @ColumnInfo(name = "item_id") val itemId: Long,
    val type: String
)