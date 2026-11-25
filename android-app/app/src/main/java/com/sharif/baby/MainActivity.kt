package com.sharif.baby

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        val phoneInput = findViewById<EditText>(R.id.phone_number)

        // ফোন কল করার জন্য — নম্বর বসানো ডায়ালার খুলবে;
        // কল শুরু করতে ডায়ালারের কল বাটনটা নিজে চাপতে হবে।
        findViewById<Button>(R.id.btn_call).setOnClickListener {
            val number = phoneInput.text.toString().trim()
            if (number.isEmpty()) {
                Toast.makeText(this, "আগে মোবাইল নম্বর লেখো", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$number")
                }
                startActivity(intent)
            }
        }

        // মেসেজ পাঠানোর জন্য — নম্বর বসানো মেসেজ অ্যাপ খুলবে;
        // লিখে পাঠানোর বাটনটা নিজে চাপতে হবে।
        findViewById<Button>(R.id.btn_sms).setOnClickListener {
            val number = phoneInput.text.toString().trim()
            if (number.isEmpty()) {
                Toast.makeText(this, "আগে মোবাইল নম্বর লেখো", Toast.LENGTH_SHORT).show()
            } else {
                val sendIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("smsto:$number")
                    putExtra("sms_body", "")
                }
                startActivity(sendIntent)
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
