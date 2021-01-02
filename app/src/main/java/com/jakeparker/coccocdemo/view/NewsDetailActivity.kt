package com.jakeparker.coccocdemo.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.jakeparker.coccocdemo.Constants
import com.jakeparker.coccocdemo.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding

    companion object {
        fun start(context: Context, title: String?, url: String) {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_NEWS_TITLE, title)
            intent.putExtra(Constants.EXTRA_NEWS_DETAIL_URL, url)
            context.startActivity(intent)
        }
    }

    private val onCloseListener = View.OnClickListener {
        onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            lifecycleOwner = this@NewsDetailActivity
            intent?.getStringExtra(Constants.EXTRA_NEWS_TITLE)?.let { title = it }
            closeListener = onCloseListener
        }

        setupWebView()
    }

    private fun setupWebView() {
        binding.apply {
            wvNewsContent.settings.loadWithOverviewMode = true
            wvNewsContent.settings.useWideViewPort = true
            wvNewsContent.settings.javaScriptEnabled = true
            wvNewsContent.settings.javaScriptCanOpenWindowsAutomatically = true

            wvNewsContent.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progress.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progress.visibility = View.GONE
                }
            }

            wvNewsContent.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    progress.progress = newProgress
                }
            }

            intent?.getStringExtra(Constants.EXTRA_NEWS_DETAIL_URL)?.let {
                wvNewsContent.loadUrl(
                    it
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.wvNewsContent.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.wvNewsContent.restoreState(savedInstanceState)
    }
}