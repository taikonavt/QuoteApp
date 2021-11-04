package com.example.core_impl.json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class DeserializeToString {

    companion object Factory : JsonAdapter.Factory {
        override fun create(
            type: Type,
            annotations: MutableSet<out Annotation>,
            moshi: Moshi
        ): JsonAdapter<*>? {
            val nextAnnotations = Types.nextAnnotations(annotations, DeserializeToString::class.java)
            return if (nextAnnotations == null) {
                null
            } else {
                DeserializeToStringJsonAdapter()
            }
        }
    }

}

class DeserializeToStringJsonAdapter: JsonAdapter<String>() {
    override fun fromJson(reader: JsonReader): String? {
        return reader.readJsonValue()?.toJson()
    }

    override fun toJson(writer: JsonWriter, value: String?) {
        throw UnsupportedOperationException(
            "@DeserializeToString is only used to deserialize objects.")
    }

}
