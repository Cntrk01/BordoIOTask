package com.example.bordoiotask.di

import com.example.bordoiotask.data.remote.BordoApi
import com.example.bordoiotask.data.repositoryimpl.BordoRepositoryImpl
import com.example.bordoiotask.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : BordoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BordoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBordoRepositoryImpl(bordoApi: BordoApi) : BordoRepositoryImpl{
        return BordoRepositoryImpl(bordoApi = bordoApi)
    }
}