package com.server.dosopt.seminar.external;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PreSignedUrlVO {

    private String fileName;
    private String url;
}
