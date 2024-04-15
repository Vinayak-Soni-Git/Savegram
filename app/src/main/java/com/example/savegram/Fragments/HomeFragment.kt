package com.example.savegram.Fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.savegram.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.apache.commons.lang3.StringUtils
import java.io.IOException

class HomeFragment : Fragment() {
    var url: String = ""
    lateinit var contentLinkEd: EditText
    lateinit var downloadButton: Button
    lateinit var mediaController: MediaController
    lateinit var uri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        contentLinkEd = view.findViewById(R.id.instagramLinkInputField)
        downloadButton = view.findViewById(R.id.downloadButton)
        mediaController = MediaController(requireContext())

        downloadButton.setOnClickListener {
            var url = contentLinkEd.text.toString().trim()
            var finalUrl: Uri
            if (url.isEmpty()) {
                Toast.makeText(requireContext(), "Enter post link", Toast.LENGTH_SHORT).show()
            } else {
                var result2: String = StringUtils.substringBefore(url, "/?")
                url = result2 + "/?__a=1&__d=dis"
            }
            Log.d("finalUrl", url.toString())

            val client = OkHttpClient()

            // Create a request with custom headers
            val request = Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36 Edg/123.0.0.0")
                .build()

            // Execute the request asynchronously
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    // Handle failure
                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }

                    // Get JSON response body
                    val responseBody = response.body()
                    responseBody?.let {
                        val json = it.string()
                        // Handle JSON response
                        println(json)
                        Log.d("response", json)
                    }
                }
            })
            
            


//            val downloadRequest:DownloadManager.Request = DownloadManager.Request(finalUrl)
//            downloadRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
//            downloadRequest.setTitle("Download")
//            downloadRequest.setDescription("........")
//            downloadRequest.allowScanningByMediaScanner()
//            downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//            downloadRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, "${System.currentTimeMillis()}")
//            val downloadManager: DownloadManager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            downloadManager.enqueue(downloadRequest)
//            Toast.makeText(requireContext(), "Downloaded", Toast.LENGTH_SHORT).show()
        }


        return view
    }


    // Add the request to the RequestQueue
    fun makeSecondRequestWithHeaders() {
        
    }
}