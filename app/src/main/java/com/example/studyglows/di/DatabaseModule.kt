package com.example.studyglows.di

import com.example.studyglows.db.dao.CoursesDAO
import com.example.studyglows.db.StudyGlowsDatabase
import com.example.studyglows.db.dao.CartDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideCoursesDao(appDatabase: StudyGlowsDatabase): CoursesDAO {
        return appDatabase.courseDao()
    }
    @Singleton
    @Provides
    fun provideCartDao(appDatabase: StudyGlowsDatabase): CartDAO {
        return appDatabase.cartDao()
    }
}