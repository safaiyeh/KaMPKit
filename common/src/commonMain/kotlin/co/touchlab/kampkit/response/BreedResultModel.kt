package co.touchlab.kampkit.response

expect interface BreedResultModel {
    val message: Map<String, List<String>>
    var status: String
}