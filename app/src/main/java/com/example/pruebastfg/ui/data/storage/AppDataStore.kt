package com.example.pruebastfg.ui.data.storage

import android.content.Context
import androidx.datastore.dataStore
import com.example.pruebastfg.ui.data.serializers.AppsSerializer

val Context.appsDataStore by dataStore(
    fileName = "user_apps.pb",
    serializer = AppsSerializer
)