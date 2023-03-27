package com.example.driveme.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.driveme.data.model.Service
import com.example.driveme.databinding.ServiceListItemBinding
import com.example.driveme.ui.WebViewActivity

class ServiceAdapter(private val serviceList: List<Service>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    inner class ServiceViewHolder(private val binding: ServiceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(service: Service) {
            Glide.with(binding.root)
                .load(service.image_url)
                .into(binding.serviceIv)

            binding.serviceNameTv.text = service.name
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, WebViewActivity::class.java)
                // redirect url from Api was null in every service so added a static url
                intent.putExtra("URL", "https://www.driveu.in/")
                intent.putExtra("Title", service.name)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ServiceListItemBinding.inflate(layoutInflater, parent, false)
        return ServiceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(serviceList[position])
    }
}
