package com.example.studyglows.di

import android.content.Context
import androidx.room.Room
import com.example.studyglows.db.StudyGlowsDatabase
import com.example.studyglows.network.models.Course
import com.example.studyglows.utils.Constants
import com.example.studyglows.utils.GsonDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Course::class.java, GsonDeserializer.CourseDeserializer())
            .create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context): StudyGlowsDatabase =
        Room.databaseBuilder(
            appContext,
            StudyGlowsDatabase::class.java,
            "StudyGlowsDB"
        ).build()
}