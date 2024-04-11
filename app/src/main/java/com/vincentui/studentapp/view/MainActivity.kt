package com.vincentui.studentapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.vincentui.studentapp.R
import com.vincentui.studentapp.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.vincentui.studentapp.util.createNotificationChannel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    init{
        instance = this
    }
    companion object{
        private var instance:MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(instance!!, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
                return
            }
            notificationManager.notify(1001, notificationBuilder.build())

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //permissions itu kek post notification nya. ini contohnya mek 1, tapi isa banyak
        //grantResult itu array juga, kalau 0 brti user ga izinin, kalo 1 brti izinin permission

        when(requestCode) {
            1 -> {
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("permission", "granted")
                    createNotificationChannel(this,
                        NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                        getString(R.string.app_name), "App notification channel.")

                } else {
                    Log.d("permission", "deny")
                }

            }
        }

    }
}