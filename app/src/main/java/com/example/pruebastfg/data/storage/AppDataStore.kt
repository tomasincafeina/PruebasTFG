package com.example.pruebastfg.data.storage

import android.content.Context
import androidx.datastore.dataStore
import com.example.pruebastfg.data.serializers.AppsSerializer

val Context.appsDataStore by dataStore(
    fileName = "user_apps.pb",
    serializer = AppsSerializer
)