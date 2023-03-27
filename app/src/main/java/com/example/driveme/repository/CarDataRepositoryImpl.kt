package com.example.driveme.repository

import com.example.driveme.data.model.ApiResponse
import com.example.driveme.data.DriveMeApi
import com.example.driveme.util.Response
import javax.inject.Inject

class CarDataRepositoryImpl @Inject constructor(
    private val driveMeApi: DriveMeApi
) : CarDataRepository {

    override suspend fun getCarData(): Response<ApiResponse> {
        return try {
            val carData = driveMeApi.getCarData()
            Response.Success(carData)

        } catch (e: Exception) {
            Response.Error(e.message ?: "An Error Occurred")
        }
    }

}