package mx.com.mercadolibre.hansolo.common.responses

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class CoordinatesResponse(

    var x: Double = 0.0,

    var y: Double = 0.0

)