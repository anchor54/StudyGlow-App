package com.example.studyglows.di

import com.example.studyglows.network.apis.LoginApis
import com.example.studyglows.utils.Constants.BASE_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ViewModelComponent::class)
@Module
class NetworkModule {

    @Provides
    fun providesLoginApi(): LoginApis =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApis::class.java)


}