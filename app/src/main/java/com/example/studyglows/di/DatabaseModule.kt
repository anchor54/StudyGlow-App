package com.example.studyglows.di

import com.example.studyglows.db.CoursesDAO
import com.example.studyglows.db.StudyGlowsDatabase
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
    fun provideChannelDao(appDatabase: StudyGlowsDatabase): CoursesDAO {
        return appDatabase.courseDao()
    }
}