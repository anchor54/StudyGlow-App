package com.example.studyglows.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.studyglows.network.models.Chapter
import com.example.studyglows.screens.home.common.models.ViewStatus
import com.google.gson.annotations.SerializedName
import java.util.UUID

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey val id: Long,
    val title: String?,
    @ColumnInfo(name = "resource_id") val resourceId: Long?,
    val about: String?,
    val language: String?,
    val price: Double?,
    val mrp: Double?,
    val startDate: String?,
    val purchaseDuration: Int?,
    val duration: Int?,
    @ColumnInfo(name = "product_code") val productCode: String?,
    @ColumnInfo(name = "category_id") val categoryId: Long?,
    @ColumnInfo(name = "subcategory_id") val subcategoryId: Long?,
    val publish: Boolean?,
)

@Entity(tableName = "course_resource")
data class Resource(
    val chapter: String?,
    val description: String?,
    val duration: Int?,
    @PrimaryKey val id: Long,
    val name: String?,
    val size: Int?,
    val type: String?,
    val url: String?
)

@Entity(tableName = "course_category")
data class Category(
    @PrimaryKey val id: Long,
    val name: String?
)

@Entity(tableName = "course_subcategory")
data class Subcategory(
    val category: Int?,
    @PrimaryKey val id: Long,
    val name: String?
)

@Entity(tableName = "course_feature")
data class Feature(
    @PrimaryKey val id: Long,
    val name: String?
)

@Entity(tableName = "faculty")
data class Faculty(
    val active: Boolean?,
    val bio: String?,
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "joining_date") val joiningDate: String?,
    val name: String?,
    val resource: String?
)

@Entity(
    tableName = "course_features",
    primaryKeys = ["course_id", "feature_id"],
    foreignKeys = [
        ForeignKey(entity = Course::class, parentColumns = ["id"], childColumns = ["course_id"]),
        ForeignKey(entity = Feature::class, parentColumns = ["id"], childColumns = ["feature_id"])
    ]
)
data class CourseFeatures(
    @ColumnInfo(name = "course_id") val courseId: Long,
    @ColumnInfo(name = "feature_id") val featureId: Long
)

@Entity(
    tableName = "course_faculties",
    primaryKeys = ["course_id", "faculty_id"],
    foreignKeys = [
        ForeignKey(entity = Course::class, parentColumns = ["id"], childColumns = ["course_id"]),
        ForeignKey(entity = Faculty::class, parentColumns = ["id"], childColumns = ["faculty_id"])
    ]
)
data class CourseFaculties(
    @ColumnInfo(name = "course_id") val courseId: Long,
    @ColumnInfo(name = "faculty_id") val facultyId: Long
)

class CourseWithResourceModel(
    @SerializedName("courseId") val courseId: String = UUID.randomUUID().toString(),
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("originalPrice") val originalPrice: Float? = null,
    @SerializedName("discountedPrice") val discountedPrice: Float? = null,
    @SerializedName("brief") val brief: String? = null
)

@Entity(tableName = "chapter_resource")
data class ChapterResource(
    @PrimaryKey val id: Long,
    val chapter: Long,
    val description: String?,
    val duration: Long?,
    val name: String?,
    val size: Long?,
    val type: String?,
    val url: String?,
    @ColumnInfo(name = "status") val playingStatus: String = ViewStatus.TO_WATCH.status
)

@Entity(tableName = "chapter")
data class Chapter(
    @PrimaryKey val id: Int,
    val course: Long,
    val name: String?
)