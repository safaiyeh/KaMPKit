package co.touchlab.kampkit.response

import kotlinx.serialization.Serializable

actual interface BreedResultModel {
    actual val message: Map<String, List<String>>
    actual var status: String
}

@Serializable
data class BreedResult(
    override val message: Map<String, List<String>>,
    override var status: String
) : BreedResultModel