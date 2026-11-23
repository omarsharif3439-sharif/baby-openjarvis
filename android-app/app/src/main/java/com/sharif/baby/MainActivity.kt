package com.sharif.baby

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

/**
 * Baby — OpenJarvis Android client.
 *
 * এই অ্যাপটা নিজে AI চালায় না। এটা শুধু OpenJarvis সার্ভারের
 * ওয়েব পর্দাটা (GUI) ফোনে দেখায়। AI মডেল চলে সার্ভারে —
 * নিজের কম্পিউটারে অথবা ক্লাউডে।
 *
 * ব্যবহারের আগে নিচের SERVER_URL-এ নিজের সার্ভারের ঠিকানা বসান:
 *  - একই Wi-Fi-তে থাকা কম্পিউটার হলে:  http://192.168.1.100:5173
 *    (192.168.1.100-এর জায়গায় কম্পিউটারের আসল IP ঠিকানা)
 *  - ক্লাউড সার্ভার হলে:               https://আপনার-সার্ভারের-ঠিকানা
 */
class MainActivity : AppCompatActivity() {

    companion object {
        // ★★★ এখানে নিজের OpenJarvis সার্ভারের ঠিকানা বসান ★★★
        private const val SERVER_URL = "http://192.168.1.100:5173"
    }

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.loadUrl(SERVER_URL)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
