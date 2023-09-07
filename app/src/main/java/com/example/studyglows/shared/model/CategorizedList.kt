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

    fun size() = categoryMap.size
    fun categorySize(category: String) = categoryMap[category]?.size ?: 0
    fun getAllCategories() = categoryMap.map { it.key }
    fun getListForCategory(category: String) = categoryMap[category] ?: listOf()
}