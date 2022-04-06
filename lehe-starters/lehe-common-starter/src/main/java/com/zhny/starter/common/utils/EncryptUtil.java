package com.zhny.starter.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author houqj
 * 2021-01-18 17:41
 */
public class EncryptUtil {

    public static final String MOBILE_REG = "^\\d{11}$";
    public static final String EMAIL_REG = "@+";

    // 手机号码前三后四脱敏
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 隐藏邮箱信息
     *
     * @param email
     * @return Result 返回结果
     */
    public static String emailEncrypt(String email) {
        if (StringUtils.isEmpty(email)) {
            return email;
        }
        String encrypt = email.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
        if (StringUtils.equalsIgnoreCase(email, encrypt)) {
            encrypt = email.replaceAll("(\\w*)\\w{1}@(\\w+)", "$1*@$2");
        }
        return encrypt;
    }

    /**
     * 隐藏字段信息
     * 如果字符长度大于3位，则隐藏最后三位，否则隐藏最后1位
     *
     * @param field
     * @return Result 返回结果
     */
    public static String fieldEncrypt(String field) {
        if (StringUtils.isEmpty(field)) {
            return field;
        }
        String encrypt = field.replaceAll("(\\w+)\\w{3}", "$1***");
        if (StringUtils.equalsIgnoreCase(field, encrypt)) {
            encrypt = field.replaceAll("(\\w*)\\w{1}", "$1*");
        }
        return encrypt;
    }

    //身份证前三后四脱敏
    public static String idEncrypt(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    //护照前2后3位脱敏，护照一般为8或9位
    public static String idPassport(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.substring(0, 2) + new String(new char[id.length() - 5]).replace("\0", "*") + id.substring(id.length() - 3);
    }

    //支付宝账号脱敏
    //手机号：隐藏中间4位
    //邮箱：如果@前字符长度大于3位，则隐藏最后三位，否则隐藏最后1位
    public static String alipayAccountEncrypt(String account) {
        if (StringUtils.isEmpty(account)) {
            return account;
        }

        Pattern pattern = Pattern.compile(MOBILE_REG);
        Matcher matcher = pattern.matcher(account);
        if (matcher.find()) {
            account = EncryptUtil.mobileEncrypt(account);
            return account;
        } else {
            pattern = Pattern.compile(EMAIL_REG);
            matcher = pattern.matcher(account);
            if (matcher.find()) {
                account = EncryptUtil.emailEncrypt(account);
                return account;
            } else {
                account = EncryptUtil.fieldEncrypt(account);
                return account;
            }
        }
    }

    //密码脱敏
    //手机号：隐藏中间4位
    //邮箱：如果@前字符长度大于3位，则隐藏最后三位，否则隐藏最后1位
    public static String passwordEncrypt(String password) {
        if (StringUtils.isEmpty(password)) {
            return password;
        }
        return "******";
    }
}
