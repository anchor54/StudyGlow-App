package com.example.studyglows.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.studyglows.db.models.CourseFaculties
import com.example.studyglows.db.models.CourseFeatures
import com.example.studyglows.db.models.Category
import com.example.studyglows.db.models.Chapter
import com.example.studyglows.db.models.ChapterResource
import com.example.studyglows.db.models.Course
import com.example.studyglows.db.models.CourseWithResourceModel
import com.example.studyglows.db.models.Feature
import com.example.studyglows.db.models.Faculty
import com.example.studyglows.db.models.Resource
import com.example.studyglows.db.models.Subcategory
import com.example.studyglows.screens.home.common.models.CourseFeature
import com.example.studyglows.screens.home.common.models.Educators

@Dao
interface CoursesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourse(course: Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addResource(resource: Resource)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubCategory(subcategory: Subcategory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFeature(feature: Feature)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFaculty(faculty: Faculty)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourseFeatureMap(map: CourseFeatures)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourseFacultyMap(map: CourseFaculties)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChapters(chapters: List<Chapter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChapterResources(chapters: List<ChapterResource>)

    @Query("SELECT courses.id as courseId, course_resource.url as imageUrl, courses.title as title, courses.mrp as originalPrice, courses.price as discountedPrice FROM courses LEFT JOIN course_resource ON courses.resource_id = course_resource.id LIMIT 5")
    suspend fun getPopularCourses(): List<CourseWithResourceModel>

    @Query("SELECT courses.id as courseId, course_resource.url as imageUrl, courses.title as title, courses.mrp as originalPrice, courses.price as discountedPrice FROM courses LEFT JOIN course_resource ON courses.resource_id = course_resource.id WHERE courses.id != :courseId LIMIT 5")
    suspend fun getSimilarCourses(courseId: Long): List<CourseWithResourceModel>

    @Query("SELECT courses.id as courseId, course_resource.url as imageUrl, courses.title as title, courses.mrp as originalPrice, courses.price as discountedPrice FROM (SELECT courses.* FROM courses, course_subcategory WHERE courses.subcategory_id = course_subcategory.id AND course_subcategory.name = :subcategory) as courses LEFT JOIN course_resource ON courses.resource_id = course_resource.id")
    suspend fun getCoursesBySubcategory(subcategory: String): List<CourseWithResourceModel>

    @Query("SELECT c.id as courseId, course_resource.url as imageUrl, c.title as title, c.mrp as originalPrice, c.price as discountedPrice FROM (SELECT courses.* FROM courses, course_subcategory WHERE courses.subcategory_id = course_subcategory.id AND course_subcategory.name = :subcategory) as c LEFT JOIN course_resource ON c.resource_id = course_resource.id LIMIT 4")
    suspend fun getCoursesForSubcategory(subcategory: String): List<CourseWithResourceModel>

    @Query("SELECT courses.id as courseId, course_resource.url as imageUrl, courses.title as courseTitle, courses.mrp as originalPrice, courses.price as discountedPrice, courses.about as brief FROM courses LEFT JOIN course_resource ON courses.resource_id = course_resource.id AND courses.id = :courseId")
    suspend fun getCourseDetails(courseId: String): CourseWithResourceModel

    @Query("SELECT id, name as title FROM course_feature INNER JOIN course_features ON course_feature.id = course_features.feature_id AND course_features.course_id = :courseId")
    suspend fun getCourseFeatures(courseId: String): List<CourseFeature>

    @Query("SELECT name as educatorName, resource as imageUrl, bio as achievements FROM faculty INNER JOIN course_faculties ON faculty.id = course_faculties.faculty_id AND course_faculties.course_id = :courseId")
    suspend fun getCourseFaculties(courseId: String): List<Educators>

    @Query("SELECT name FROM course_category")
    suspend fun getAllCategories(): List<String>

    @Query("SELECT name FROM faculty WHERE faculty.active = 1")
    suspend fun getAllEducators(): List<String>

    @Query("SELECT name FROM course_subcategory")
    suspend fun getAllSubcategories(): List<String>

    @RawQuery
    suspend fun getFilteredCourses(query: SupportSQLiteQuery): List<CourseWithResourceModel>

    @Query("SELECT courses.id as courseId, course_resource.url as imageUrl, courses.title as title, courses.mrp as originalPrice, courses.price as discountedPrice FROM courses LEFT JOIN course_resource ON courses.resource_id = course_resource.id WHERE courses.title LIKE :searchString OR courses.about LIKE :searchString")
    suspend fun searchCourse(searchString: String): List<CourseWithResourceModel>
}