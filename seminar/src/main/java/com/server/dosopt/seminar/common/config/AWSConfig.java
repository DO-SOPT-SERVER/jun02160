package com.server.dosopt.seminar.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class AWSConfig {

    private static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
    private static final String AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey";

    private final String accessKey;
    private final String secretKey;
    private final String regionString;

    // 처음부터 @Value로 환경변수를 가져오는 방식이 아닌, final로 상수를 선언하기 위해 생성자에서 @Value로 가져와 넣어주는 방식도 있음!
    public AWSConfig(@Value("${aws-property.access-key}") final String accessKey,
                     @Value("${aws-property.secret-key}") final String secretKey,
                     @Value("${aws-property.aws-region}") final String regionString) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.regionString = regionString;
    }


    // Java System에 환경변수 등록 => 애플리케이션 실행 시 등록된 값을 가져올 수 있다!
    @Bean
    public SystemPropertyCredentialsProvider systemPropertyCredentialsProvider() {
        System.setProperty(AWS_ACCESS_KEY_ID, accessKey);
        System.setProperty(AWS_SECRET_ACCESS_KEY, secretKey);
        return SystemPropertyCredentialsProvider.create();   // 임시 자격증명 획득
    }

    @Bean
    public Region getRegion() {
        return Region.of(regionString);
    }

    @Bean
    public S3Client getS3Client() {
        return S3Client.builder()
                .region(getRegion())
                .credentialsProvider(systemPropertyCredentialsProvider())
                .build();
    }

    /**
     * 서버에서 정적 파일을 관리하는 부담을 덜어주기 위해, Presigned Url을 사용하는 방식이 많이 사용됨
     * -> 클라이언트에 파일 저장 경로에 대한 요청과 응답 후,
     * @return
     */
    @Bean
    S3Presigner getS3Presigner() {
        return S3Presigner.builder()
                .region(getRegion())
                .credentialsProvider(systemPropertyCredentialsProvider())
                .build();
    }
}
