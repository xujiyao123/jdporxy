package com.xujiyao.jdporxy.exception;

/**
 * Created by xujiyao on 2017/4/15.
 * StudentService
 * ${PACKAGE_NAME}
 */
public class DataNoteFoundException extends Exception {


    public DataNoteFoundException() {
    }

    public DataNoteFoundException(String message) {
        super(message);
    }

    public DataNoteFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNoteFoundException(Throwable cause) {
        super(cause);
    }

    public DataNoteFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
