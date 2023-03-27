package com.example.driveme.data

import com.example.driveme.data.model.ApiResponse
import retrofit2.http.GET

interface DriveMeApi {

    @GET("v1/a53f9e9d-edf0-496c-a705-33040569a7da")
    suspend fun getCarData() : ApiResponse
}