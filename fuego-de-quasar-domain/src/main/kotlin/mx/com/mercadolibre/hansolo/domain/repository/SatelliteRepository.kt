package mx.com.mercadolibre.hansolo.domain.repository

import mx.com.mercadolibre.hansolo.domain.model.Satellite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

@Repository
interface SatelliteRepository : JpaRepository<Satellite, Long> {

    fun findByName(name: String): Satellite?

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE public.satellite SET distance=:distance, message=:message, updated_at=:updated_at WHERE name=:name", nativeQuery = true)
    fun updateByName(
        @Param("distance") distance: Double,
        @Param("message") message: String,
        @Param("updated_at") updated_at: Timestamp,
        @Param("name") name: String
    ): Int

}