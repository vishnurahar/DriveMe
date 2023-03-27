package com.example.driveme.ui.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.driveme.data.model.Banner
import com.example.driveme.databinding.BannersListItemBinding

class BannerAdapter(private val banners: List<Banner>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BannersListItemBinding.inflate(inflater, parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = banners[position]
        holder.bind(banner)
    }

    override fun getItemCount(): Int {
        return banners.size
    }

    inner class BannerViewHolder(private val binding: BannersListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(banner: Banner) {
            Glide.with(binding.root)
                .load(banner.image)
                .into(binding.bannerIv)

            binding.root.setOnClickListener {
                val builder = AlertDialog.Builder(binding.root.context)
                builder.setTitle(banner.provider_name)
                builder.setMessage(banner.name)
                builder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show()
            }

        }
    }
}
