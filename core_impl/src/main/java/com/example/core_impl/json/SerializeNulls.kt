package com.example.core_impl.json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class SerializeNulls {

    companion object Factory : JsonAdapter.Factory {
        override fun create(
            type: Type,
            annotations: MutableSet<out Annotation>,
            moshi: Moshi
        ): JsonAdapter<*>? {
            val nextAnnotations = Types.nextAnnotations(annotations, SerializeNulls::class.java)
            return if (nextAnnotations == null) {
                null
            } else {
                moshi.nextAdapter<Any>(this, type, nextAnnotations).serializeNulls()
            }
        }
    }

}
