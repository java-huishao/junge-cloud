package com.zhny.starter.oss.service;


import com.zhny.starter.oss.common.FileUploadResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author houqj
 * @date 2021-07-06 22:11
 */
public interface IUploadFileService {

    /**
     * 上传文件
     *
     * @param originalFilename 源文件名
     * @param fileSize         长度
     * @param contentType      类型
     * @param inputStream      输入流
     * @return FileUploadResult
     */
    FileUploadResult uploadFile(String originalFilename, Long fileSize, String contentType, InputStream inputStream) throws IOException;

    /**
     * 下载文件
     *
     * @param response
     * @param fileName
     */
    void downloadFile(HttpServletResponse response, String fileName);
}
