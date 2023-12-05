package sopt.org.kotlinseminar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class KotlinSeminarApplication

fun main(args: Array<String>) {
    runApplication<KotlinSeminarApplication>(*args)
}
