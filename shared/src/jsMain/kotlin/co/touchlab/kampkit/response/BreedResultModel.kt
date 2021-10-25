package co.touchlab.kampkit.response

@ExperimentalJsExport
@JsExport
actual external interface BreedResultModel {
    actual val message: Map<String, List<String>>
    actual var status: String
}