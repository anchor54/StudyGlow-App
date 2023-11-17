package com.example.studyglows.shared.model

class CategorizedList<T>(
    private val categoryMap: Map<String, List<T>> = mapOf()
) {
    fun modifyOrAddItem(
        category: String,
        item: T,
        idx: Int? = null
    ): CategorizedList<T> {
        val newCategoryMap = categoryMap.toMutableMap()
        val items = newCategoryMap[category]?.toMutableList() ?: mutableListOf()
        if (idx != null && items.size > idx) {
            items[idx] = item
        } else {
            items.add(item)
        }
        newCategoryMap[category] = items
        return CategorizedList(newCategoryMap)
    }

    fun removeItem(
        category: String,
        item: T
    ): CategorizedList<T> {
        val newCategoryMap = categoryMap.toMutableMap()
        val items = newCategoryMap[category]?.filter { it != item } ?: listOf()
        newCategoryMap[category] = items
        return CategorizedList(newCategoryMap)
    }

    fun clearList(category: String): CategorizedList<T> {
        val newCategoryMap = categoryMap.toMutableMap()
        newCategoryMap[category] = listOf()
        return CategorizedList(newCategoryMap)
    }

    fun clearAll(): CategorizedList<T> =
        CategorizedList(getAllCategories().associateWith { listOf() })

    fun getAllItems(): List<T> =
        categoryMap.flatMap { it.value }

    fun size() = categoryMap.size
    fun categorySize(category: String) = categoryMap[category]?.size ?: 0
    fun getAllCategories() = categoryMap.map { it.key }
    fun getListForCategory(category: String) = categoryMap[category] ?: listOf()
}