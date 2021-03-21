package com.example.planapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.renderscript.ScriptGroup
import android.util.LruCache
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object ImageDownloader {
    private var memoryCache: LruCache<String, Bitmap>
    init {
        val maxMemory: Int = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize: Int = maxMemory / 8
        memoryCache = object : LruCache<String, Bitmap>(cacheSize){
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount / 1024
            }
        }
    }

    fun download(image: ImageView, url: String, arroundBorder: Int){
        val imageKey: String = url
        val bitmap: Any? = getBitmapFromMemoryCache(imageKey)?.also {
            val roundedBitmap: RoundedBitmapDrawable = roundedBitmapImage(image, it, arroundBorder)
            image.setImageDrawable(roundedBitmap)
        } ?: kotlin.run{
            Thread {
                val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
                try {
                    val responseCode = connection.responseCode
                    if (responseCode == 200) {
                        val inputStream: InputStream = connection.inputStream
                        val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
                        val roundedBitmap: RoundedBitmapDrawable = roundedBitmapImage(image, bitmap, arroundBorder)
                        image.setImageDrawable(roundedBitmap)
                        memoryCache.put(url, bitmap)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }.start()
        }
    }
    private fun roundedBitmapImage(image: ImageView, bitmap: Bitmap, arroundBorder: Int): RoundedBitmapDrawable{
       val rbd: RoundedBitmapDrawable =  RoundedBitmapDrawableFactory.create(image.resources, bitmap)
        rbd.cornerRadius = (bitmap.width / arroundBorder).toFloat()
        return rbd
    }
    private fun getBitmapFromMemoryCache(imageKey: String): Bitmap?{
        return memoryCache[imageKey]
    }
}