package co.touchlab.kampkit

expect inline fun <reified T> encodeToString(value: T): String
expect inline fun <reified T> decodeFromString(value: String): T
