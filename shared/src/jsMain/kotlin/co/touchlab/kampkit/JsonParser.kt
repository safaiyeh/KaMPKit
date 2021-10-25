package co.touchlab.kampkit

@Suppress("NOTHING_TO_INLINE")
actual inline fun <T> encodeToString(value: T): String = JSON.stringify(value)

@Suppress("NOTHING_TO_INLINE")
actual inline fun <T> decodeFromString(value: String): T = JSON.parse(value)
