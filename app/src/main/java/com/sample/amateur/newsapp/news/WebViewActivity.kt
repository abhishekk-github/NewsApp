package com.sample.amateur.newsapp.news

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.sample.amateur.newsapp.R

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val learn2crack = findViewById<WebView>(R.id.webview)
        learn2crack.loadUrl(intent.getStringExtra("URL"))
        learn2crack.settings.javaScriptEnabled = true
    }
}
