package com.example.studyglows.di

import com.example.studyglows.network.apis.CourseApiService
import com.example.studyglows.network.apis.LoginApis
import com.example.studyglows.utils.Constants.BASE_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// This module provides dependencies for network operations in an Android application using Dagger Hilt, including setting up Retrofit with Gson converter and creating instances of API services for login and courses.
@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun providesLoginApi(retrofit: Retrofit): LoginApis = retrofit.create(LoginApis::class.java)

    @Provides
    fun providesCourseApiService(retrofit: Retrofit): CourseApiService = retrofit.create(CourseApiService::class.java)

}