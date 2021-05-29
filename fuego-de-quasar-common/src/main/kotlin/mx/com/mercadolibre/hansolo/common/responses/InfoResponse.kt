package mx.com.mercadolibre.hansolo.common.responses

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class InfoResponse(

    var position: CoordinatesResponse = CoordinatesResponse(),

    var message: String = ""

)