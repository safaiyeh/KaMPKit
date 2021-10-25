package co.touchlab.kampkit.response

actual interface BreedResultModel {
    actual val message: Map<String, List<String>>
    actual var status: String
}