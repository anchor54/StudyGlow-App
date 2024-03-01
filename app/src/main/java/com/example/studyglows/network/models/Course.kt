package com.example.studyglows.network.models


class CourseList : ArrayList<Course>()
data class Course(
    val id: Long,
    val title: String?,
    val resource: Resource?,
    val about: String?,
    val language: String?,
    val price: Double?,
    val mrp: Double?,
    val startDate: String?,
    val purchaseDuration: Int?,
    val duration: Int?,
    val productCode: String?,
    val category: Category?,
    val subcategory: Subcategory?,
    val features: List<Feature>?,
    val facuties: List<Faculty>?,
    val publish: Boolean?,
)

data class Resource(
    val chapter: String?,
    val description: String?,
    val duration: Int?,
    val id: Long,
    val name: String?,
    val size: Int?,
    val type: String?,
    val url: String?
)

data class Category(
    val id: Long,
    val name: String?
)

data class Subcategory(
    val category: Int?,
    val id: Long,
    val name: String?
)

data class Feature(
    val id: Long,
    val name: String?
)

data class Faculty(
    val active: Boolean?,
    val bio: String?,
    val id: Long,
    val joiningDate: String?,
    val name: String?,
    val resource: String?
)

class ChapterList: ArrayList<Chapter>()
data class Chapter(
    val course: Int,
    val id: Int,
    val name: String
)

class ChapterResourceList : ArrayList<ChapterResource>()

data class ChapterResource(
    val chapter: Chapter,
    val description: String,
    val duration: Int,
    val id: Int,
    val name: String,
    val size: Int,
    val type: String,
    val url: String
)

data class CartPostRequestBody(
    val type: String,
    val course_id: Long
)

data class Cart(
    val id: Int,
    val is_checkout: Boolean,
    val product_quantity: List<CartItem>?
)

data class CartItem(
    val amount: Double?,
    val coupon_amount: Double?,
    val course: Course?,
    val extra: String?,
    val id: Long,
    val tax_amount: Double?,
    val test_series: String?,
    val total_amount: Double?,
    val type: String?
)