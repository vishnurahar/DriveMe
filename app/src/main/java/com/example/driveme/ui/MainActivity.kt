package com.example.driveme.ui

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.driveme.R
import com.example.driveme.data.model.Feed
import com.example.driveme.databinding.ActivityMainBinding
import com.example.driveme.ui.adapter.FeedAdapter
import com.example.driveme.util.Response
import com.example.driveme.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.progressbar.visibility = View.VISIBLE
        mainViewModel.getCarData()

        mainViewModel.carDataResult.observe(this) { response->
            when(response){
                is Response.Loading -> {}
                is Response.Success -> {
                    response.data?.let {
                        binding.progressbar.visibility = View.GONE
                        val layoutManager = LinearLayoutManager(this)
                        binding.mainRv.layoutManager = layoutManager
                        binding.mainRv.adapter = FeedAdapter(this, it.feeds)

                    }
                }
                is Response.Error -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}