package com.server.dosopt.seminar.external;

import com.server.dosopt.seminar.common.config.AWSConfig;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
public class S3Service {

    private final String bucketName;
    private final AWSConfig awsConfig;

    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/webp");  // 특정 형식의 파일 업로드는 제한한다
    private static final Long MAX_FILE_SIZE = 5 * 1024 * 1024L;   // 5MB

    // 만료시간 1분
    private static final Long PRE_SIGNED_URL_EXPIRE_MINUTE = 1L;

    // lombok 어노테이션 대신에 생성자 주입으로 @Value를 안에서 넣어줌!
    public S3Service(@Value("${aws-property.s3-bucket-name}") final String bucketName, AWSConfig awsConfig) {
        this.bucketName = bucketName;
        this.awsConfig = awsConfig;
    }

    // 각각 PutObject, DeleteObject를 만들어서 버킷에 추가 및 삭제
    public String uploadImage(String directoryPath, MultipartFile image) throws IOException {
        validateExtension(image);
        validateFileSize(image);

        final String key = directoryPath + generateImageFileName();
        final S3Client s3Client = awsConfig.getS3Client();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        // 이미지를 여러 개의 바이트코드 형태로 쪼개서 요청 바디에 실어서 보냄
        RequestBody requestBody = RequestBody.fromBytes(image.getBytes());
        s3Client.putObject(request, requestBody);
        return key;
    }

    public PreSignedUrlVO getUploadPreSignedUrl(final String prefix) {
        final String fileName = generateImageFileName();
        final String key = prefix + fileName;

        S3Presigner presigner = awsConfig.getS3Presigner();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectPresignRequest preSignedUrlRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
                .putObjectRequest(putObjectRequest)
                .build();

        String url = presigner.presignPutObject(preSignedUrlRequest).url().toString();

        return PreSignedUrlVO.of(fileName, url);
    }

    public void deleteImage(String key) throws IOException {
        final S3Client s3Client = awsConfig.getS3Client();

        s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
                builder.bucket(bucketName)
                        .key(key)
                        .build());
    }

    private void validateExtension(MultipartFile image) {
        String contentType = image.getContentType();
        if (!IMAGE_EXTENSIONS.contains(contentType)) {
            throw new RuntimeException("이미지 확장자는 jpg, png, webp만 가능합니다.");
        }
    }

    private void validateFileSize(MultipartFile image) {
        if (image.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("이미지 사이즈는 5MB를 넘을 수 없습니다.");
        }
    }

    // 보안을 위해 UUID를 붙여서 파일명 저장 -> 고유 식별값으로 사용하기 위함
    private String generateImageFileName() {
        return UUID.randomUUID().toString() + ".jpg";
    }
}
