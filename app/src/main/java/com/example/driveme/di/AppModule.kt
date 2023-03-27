package com.example.driveme.di

import com.example.driveme.data.DriveMeApi
import com.example.driveme.repository.CarDataRepository
import com.example.driveme.repository.CarDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://mocki.io/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): DriveMeApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DriveMeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(driveMeApi: DriveMeApi): CarDataRepository {
        return CarDataRepositoryImpl(driveMeApi)
    }
}