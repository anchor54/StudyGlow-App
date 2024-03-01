package com.example.studyglows.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studyglows.db.models.Cart
import com.example.studyglows.db.models.CourseWithResourceModel
import com.example.studyglows.db.models.SavedItem

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cart: Cart)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(item: SavedItem)

    @Query("DELETE FROM saved_items WHERE saved_items.item_id = :id AND saved_items.type = :type")
    suspend fun deleteSavedItem(id: Long, type: String)

    @Query("DELETE FROM cart WHERE cart.item_id = :id AND cart.type = :type")
    suspend fun deleteCartItem(id: Long, type: String)

    @Query("SELECT courses.id as courseId, course_resource.url as imageUrl, courses.title as title, courses.mrp as originalPrice, courses.price as discountedPrice FROM (SELECT * FROM courses INNER JOIN cart ON courses.id = cart.item_id AND cart.type = 'COURSE') as courses LEFT JOIN course_resource ON courses.resource_id = course_resource.id")
    suspend fun getCartCourses(): List<CourseWithResourceModel>

    @Query("SELECT courses.id as courseId, course_resource.url as imageUrl, courses.title as title, courses.mrp as originalPrice, courses.price as discountedPrice FROM (SELECT * FROM courses INNER JOIN saved_items ON courses.id = saved_items.item_id AND saved_items.type = 'COURSE') as courses LEFT JOIN course_resource ON courses.resource_id = course_resource.id")
    suspend fun getSavedCourses(): List<CourseWithResourceModel>

    @Query("SELECT * FROM cart WHERE item_id = :id AND type = :type")
    suspend fun getCartItem(id: Long, type: String): Cart?

    @Query("SELECT * FROM saved_items WHERE item_id = :id AND type = :type")
    suspend fun getSavedItem(id: Long, type: String): SavedItem?
}