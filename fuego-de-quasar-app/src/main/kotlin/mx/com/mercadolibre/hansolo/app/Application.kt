package mx.com.mercadolibre.hansolo.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan("mx.com.mercadolibre.hansolo")
@EntityScan("mx.com.mercadolibre.hansolo.domain.model")
@EnableJpaRepositories("mx.com.mercadolibre.hansolo.domain.repository")
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}