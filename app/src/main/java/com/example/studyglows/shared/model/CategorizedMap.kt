package com.example.studyglows.shared.model

class CategorizedMap<K, V>(
    private val categorizedMap: Map<String, Map<K, V>> = mapOf()
) {
    fun modifyOrAddItem(category: String, key: K, value: V): CategorizedMap<K, V> {
        val newCategoryMap = categorizedMap.toMutableMap()
        val itemMap = newCategoryMap[category]?.toMutableMap() ?: mutableMapOf()
        itemMap[key] = value
        newCategoryMap[category] = itemMap
        return CategorizedMap(newCategoryMap)
    }

    fun size() = categorizedMap.size
    fun categorySize(category: String) = categorizedMap[category]?.size ?: 0
    fun getAllCategories() = categorizedMap.map { it.key }
    fun getMapForCategory(category: String) = categorizedMap[category] ?: mapOf()
}