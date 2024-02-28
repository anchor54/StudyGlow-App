package com.example.studyglows.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studyglows.db.models.Course
import com.example.studyglows.db.models.Resource
import com.example.studyglows.db.models.Category
import com.example.studyglows.db.models.Chapter
import com.example.studyglows.db.models.ChapterResource
import com.example.studyglows.db.models.CourseFaculties
import com.example.studyglows.db.models.Feature
import com.example.studyglows.db.models.CourseFeatures
import com.example.studyglows.db.models.Subcategory
import com.example.studyglows.db.models.Faculty

@Database(entities = [
    Course::class,
    Resource::class,
    Category::class,
    Subcategory::class,
    Feature::class,
    Faculty::class,
    CourseFeatures::class,
    CourseFaculties::class,
    Chapter::class,
    ChapterResource::class
 ], version = 1)
abstract class StudyGlowsDatabase: RoomDatabase() {
    abstract fun courseDao(): CoursesDAO
}