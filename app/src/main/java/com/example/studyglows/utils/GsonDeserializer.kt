package com.example.studyglows.utils

import com.example.studyglows.network.models.Course
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import java.lang.reflect.Type

object GsonDeserializer {
    class CourseDeserializer: JsonDeserializer<Course> {
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Course {
            val jsonObject = json.asJsonObject
            for (entry in jsonObject.entrySet()) {
                if (entry.value.isJsonPrimitive) {
                    val value = entry.value.asJsonPrimitive
                    if (value.isString && value.asString.isEmpty()) {
                        entry.setValue(JsonNull.INSTANCE)
                    }
                }
            }
            return Gson().fromJson(jsonObject, Course::class.java)
        }

    }
}