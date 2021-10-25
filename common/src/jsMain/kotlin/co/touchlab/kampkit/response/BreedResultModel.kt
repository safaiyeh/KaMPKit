package co.touchlab.kampkit.response

@JsExport
actual external interface BreedResultModel {
    actual val message: Map<String, List<String>>
    actual var status: String
}

@JsExport
data class BreedResult(
    override val message: Map<String, List<String>>,
    override var status: String
) : BreedResultModel