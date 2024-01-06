package sopt.org.kotlinseminar.external

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import sopt.org.kotlinseminar.common.config.AWSConfig
import java.io.IOException
import java.util.*


private val IMAGE_EXTENSIONS: List<String> = mutableListOf("image/jpeg", "image/png", "image/jpg", "image/webp")
private const val MAX_FILE_SIZE: Long = 5 * 1024 * 1024L

@Component
class S3Service(
        @Value("\${aws-property.s3-bucket-name}")
        private val bucketName: String,
        private val awsConfig: AWSConfig

) {

    @Throws(IOException::class)
    fun uploadImage(directoryPath: String, image: MultipartFile): String {
        validateExtension(image)
        validateFileSize(image)

        val key = directoryPath + generateImageFileName()
        val s3Client = awsConfig.getS3Client()

        val request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build()

        val requestBody = RequestBody.fromBytes(image.bytes)
        s3Client.putObject(request, requestBody)
        return key
    }

    @Throws(IOException::class)
    fun deleteImage(key: String?) {
        val s3Client = awsConfig.getS3Client()
        s3Client.deleteObject { builder: DeleteObjectRequest.Builder ->
            builder.bucket(bucketName)
                    .key(key)
                    .build()
        }
    }

    private fun validateExtension(image: MultipartFile) {
        val contentType = image.contentType
        if (!IMAGE_EXTENSIONS.contains(contentType)) {
            throw RuntimeException("이미지 확장자는 jpg, png, webp만 가능합니다.")
        }
    }

    private fun validateFileSize(image: MultipartFile) {
        if (image.size > MAX_FILE_SIZE) {
            throw RuntimeException("이미지 사이즈는 5MB를 넘을 수 없습니다.")
        }
    }

    private fun generateImageFileName(): String {
        return UUID.randomUUID().toString() + ".jpg"
    }
}