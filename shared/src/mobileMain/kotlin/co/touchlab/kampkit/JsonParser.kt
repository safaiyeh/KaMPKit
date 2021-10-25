package co.touchlab.kampkit

import co.touchlab.kampkit.response.BreedResult
import co.touchlab.kampkit.response.BreedResultModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val module = SerializersModule {
    polymorphic(BreedResultModel::class) {
        subclass(BreedResult::class)
    }
}

val format = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    serializersModule = module
}

actual inline fun <reified T> decodeFromString(value: String): T = format.decodeFromString(value)

actual inline fun <reified T> encodeToString(value: T): String = format.encodeToString(value)
