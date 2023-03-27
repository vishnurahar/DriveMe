package com.example.driveme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.driveme.data.model.ApiResponse
import com.example.driveme.util.Response
import com.example.driveme.repository.CarDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val carDataRepository: CarDataRepository
)  : ViewModel() {

    private val _carDataResult = MutableLiveData<Response<ApiResponse>>()
    val carDataResult: LiveData<Response<ApiResponse>>
        get() = _carDataResult

    fun getCarData() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                carDataRepository.getCarData()
            }
            _carDataResult.postValue(result)
        }
    }
}