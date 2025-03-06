package com.example.pruebastfg.ui.data.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.pruebastfg.UserApps
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object AppsSerializer : Serializer<UserApps> {
    override val defaultValue: UserApps = UserApps.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserApps {
        try {
            return UserApps.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: UserApps, output: OutputStream) {
        t.writeTo(output)
    }
}