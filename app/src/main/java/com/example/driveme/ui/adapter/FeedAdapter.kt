package com.example.driveme.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.driveme.data.model.Feed
import com.example.driveme.databinding.BannerLayoutBinding
import com.example.driveme.databinding.GarageCarLayoutBinding
import com.example.driveme.databinding.ServicesLayoutBinding

class FeedAdapter(val context: Context, private val items: List<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_CAR -> CarViewHolder(GarageCarLayoutBinding.inflate(inflater, parent, false))
            VIEW_TYPE_SERVICE -> ServicesViewHolder(ServicesLayoutBinding.inflate(inflater, parent, false))
            VIEW_TYPE_BANNER -> BannersViewHolder(BannerLayoutBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feedItem = items[position]
        when (holder.itemViewType) {
            VIEW_TYPE_CAR -> {
                val carViewHolder = holder as CarViewHolder
                carViewHolder.bind(feedItem)
            }
            VIEW_TYPE_SERVICE -> {
                val servicesViewHolder = holder as ServicesViewHolder
                servicesViewHolder.bind(feedItem)
            }
            VIEW_TYPE_BANNER -> {
                val bannersViewHolder = holder as BannersViewHolder
                bannersViewHolder.bind(feedItem)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].screen) {
            "garage_cars" -> VIEW_TYPE_CAR
            "services" -> VIEW_TYPE_SERVICE
            "banners" -> VIEW_TYPE_BANNER
            else -> {
                throw IllegalArgumentException("Invalid feed type")
            }
        }
    }

    inner class CarViewHolder(private val binding: GarageCarLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(carFeed: Feed) {
            binding.carNameTv.text = carFeed.car_name
            binding.registrationTv.text = carFeed.car_reg_no
            binding.carTypeTv.text = "${carFeed.car_type} - ${carFeed.fuel_type}"
            Glide.with(binding.root)
                .load(carFeed.image_url)
                .into(binding.garageCarIv)
        }
    }

    inner class ServicesViewHolder(private val binding: ServicesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(servicesFeed: Feed) {

            val servicesAdapter = servicesFeed.services?.let { ServiceAdapter(it) }
            binding.servicesTv.text = servicesFeed.heading
            binding.servicesRv.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = servicesAdapter
            }
        }
    }

    inner class BannersViewHolder(private val binding: BannerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bannersFeed: Feed) {
            binding.offersTv.text = bannersFeed.heading
            val bannersAdapter = bannersFeed.banners?.let { BannerAdapter(it) }
            binding.bannerRv.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = bannersAdapter
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_CAR = 0
        private const val VIEW_TYPE_SERVICE = 1
        private const val VIEW_TYPE_BANNER = 2
    }
}
