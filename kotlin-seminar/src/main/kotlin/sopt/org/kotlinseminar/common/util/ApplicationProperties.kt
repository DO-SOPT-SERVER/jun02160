package sopt.org.kotlinseminar.common.util

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring")
data class ApplicationProperties(
        val databaseProperties: DatabaseProperties,
        val awsProperties: AWSProperties
) {


    data class DatabaseProperties(
            val url: String,
            val driver: String,
            val username: String,
            val password: String
    )

    data class AWSProperties(
            val accessKey: String,
            val secretKey: String,
            val awsRegion: String,
            val s3BucketName: String
    )
}

