package co.touchlab.kampkit.ktor

import co.touchlab.kampkit.response.BreedResultModel

interface KtorApi {
    suspend fun getJsonFromApi(): BreedResultModel
}
