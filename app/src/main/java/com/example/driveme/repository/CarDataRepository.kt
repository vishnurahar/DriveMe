package com.example.driveme.repository

import com.example.driveme.data.model.ApiResponse
import com.example.driveme.util.Response

interface CarDataRepository {
    suspend fun getCarData() : Response<ApiResponse>
}