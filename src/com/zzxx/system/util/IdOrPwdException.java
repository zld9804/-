package com.zzxx.system.util;

/**
 * 自定义异常，只需要添加构造器
 */
public class IdOrPwdException extends Exception {
    public IdOrPwdException() {
    }

    public IdOrPwdException(String message) {
        super(message);
    }
}
