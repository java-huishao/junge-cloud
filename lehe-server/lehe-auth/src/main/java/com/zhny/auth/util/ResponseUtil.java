package com.zhny.auth.util;

import com.google.gson.Gson;
import com.zhny.starter.common.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseUtil {

    public static void out(HttpServletResponse response, Result result) {
        PrintWriter out = null;
        Gson gson = new Gson();
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(new Gson().toJson(result));
        } catch (Exception e) {
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

}
