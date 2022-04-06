package com.zhny.starter.common.utils;

import java.io.*;

/**
 * @author Administrator
 * 2021-07-06 23:01
 */
public class FileStreamUtil {

    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // inputStream转outputStream
    public static ByteArrayOutputStream InputStream2ByteArrayOutputStream(final InputStream in) throws Exception {
        final ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            swapStream.write(ch);
        }
        return swapStream;
    }

    // outputStream转inputStream
    public static ByteArrayInputStream OutputStream2ByteArrayInputStream(final OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        final ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    // inputStream转String
    public static String InputStream2String(final InputStream in) throws Exception {
        final ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            swapStream.write(ch);
        }
        return swapStream.toString();
    }

    // OutputStream 转String
    public static String OutputStream2String(final OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        final ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream.toString();
    }

    // String转inputStream
    public static ByteArrayInputStream String2ByteArrayInputStream(final String in) throws Exception {
        final ByteArrayInputStream input = new ByteArrayInputStream(in.getBytes());
        return input;
    }

    // String 转outputStream
    public static ByteArrayOutputStream String2ByteArrayOutputStream(final String in) throws Exception {
        return InputStream2ByteArrayOutputStream(String2ByteArrayInputStream(in));
    }


    public static void main(String[] args) {
//        ByteArrayOutputStream baos = FileStreamUtil.cloneInputStream(file.getInputStream());
//        // 打开两个新的输入流
//        InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
//        InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
//        InputStream stream3 = new ByteArrayInputStream(baos.toByteArray());
    }
}
