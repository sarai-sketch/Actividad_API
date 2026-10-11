package com.cyberqhatu.attackontitan.data.model

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/** Acepta "Eldia" o ["Eldia"] y siempre devuelve un String. */
class StringOrListAdapter : TypeAdapter<String?>() {

    override fun write(out: JsonWriter, value: String?) {
        out.value(value)
    }

    override fun read(reader: JsonReader): String? = when (reader.peek()) {
        JsonToken.NULL -> {
            reader.nextNull()
            null
        }
        JsonToken.BEGIN_ARRAY -> {
            val items = mutableListOf<String>()
            reader.beginArray()
            while (reader.hasNext()) items.add(reader.nextString())
            reader.endArray()
            items.joinToString(", ")
        }
        else -> reader.nextString()
    }
}
