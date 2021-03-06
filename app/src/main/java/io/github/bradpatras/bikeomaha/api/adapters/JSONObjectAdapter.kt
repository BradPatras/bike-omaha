package io.github.bradpatras.bikeomaha.api.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject

class JSONObjectAdapter {

    @FromJson
    fun fromJson(reader: JsonReader): JSONObject? {
        return (reader.readJsonValue() as? Map<*, *>)?.let { map ->
            try {
                JSONObject(map)
            } catch (e: JSONException) {
                return null
            }
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: JSONObject?) {
        value?.let { writer.value(Buffer().writeUtf8(value.toString())) }
    }
}