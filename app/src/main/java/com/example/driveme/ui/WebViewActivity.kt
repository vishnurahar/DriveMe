package com.example.driveme.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.driveme.R
import com.example.driveme.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)

        val url = intent.getStringExtra("URL")
        val title = intent.getStringExtra("Title")

        binding.headingTv.text = title

        if (url != null) {
            with(binding){
                webView.settings.javaScriptEnabled = true
                webView.loadUrl(url)
            }

        }
    }
}