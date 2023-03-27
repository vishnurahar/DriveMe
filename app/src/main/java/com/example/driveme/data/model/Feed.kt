package com.example.driveme.data.model

data class Feed(
    val banners: List<Banner>?,
    val car_name: String?,
    val car_reg_no: String?,
    val car_type: String?,
    val component_position: Int,
    val fuel_type: String?,
    val heading: String?,
    val image_url: String?,
    val screen: String,
    val show_all_services: Boolean?,
    val services: List<Service>?,
)