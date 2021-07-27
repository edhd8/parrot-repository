package io.parrotsoftware.pos.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan("io.parrotsoftware.pos")
@EntityScan("io.parrotsoftware.pos.domain.model")
@EnableJpaRepositories("io.parrotsoftware.pos.domain.repository")
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}