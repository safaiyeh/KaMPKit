package co.touchlab.kampkit.response

import co.touchlab.kampkit.annotation.Serializable
import kotlin.js.JsExport

expect interface BreedResultModel {
    val message: Map<String, List<String>>
    var status: String
}

@JsExport
@Serializable
data class BreedResult(
    override val message: Map<String, List<String>>,
    override var status: String
) : BreedResultModel