package com.zhny.starter.oss.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author houqj
 * 2021-01-30 19:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResult {
    private String originalFileName;
    private Long fileSize;
    private String fileType;
    private String fileName;
    private String fileUrl;
    private String downloadUrl;
}
