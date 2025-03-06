package com.example.pruebastfg.ui.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.protobuf.ByteString
import java.io.ByteArrayOutputStream

// Convertir Bitmap a ByteString
fun Bitmap.toByteString(): ByteString {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return ByteString.copyFrom(stream.toByteArray())
}

// Convertir ByteString a Bitmap
fun ByteString.toBitmap(): Bitmap {
    return BitmapFactory.decodeByteArray(this.toByteArray(), 0, this.size())
}