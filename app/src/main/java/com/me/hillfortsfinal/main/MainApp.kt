package com.me.hillfortsfinal.main

import android.app.Application
import com.me.hillfortsfinal.models.firebase.PlacemarkFireStore
import com.me.hillfortsfinal.models.PlacemarkStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainApp : Application(), AnkoLogger {

    lateinit var placemarks: PlacemarkStore

    override fun onCreate() {
        super.onCreate()
        // placemarks = PlacemarkStoreRoom(applicationContext)
        placemarks = PlacemarkFireStore(applicationContext)
        info("Placemark started")
    }
}