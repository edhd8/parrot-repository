package mx.com.mercadolibre.hansolo.domain.repository

import mx.com.mercadolibre.hansolo.domain.model.Satellite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SatelliteRepository : JpaRepository<Satellite, Long> {

    fun findByName(name: String): Satellite?

}