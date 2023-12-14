package com.spmystery.episode.exception;

public class DramaException extends RuntimeException {

    private String code;

    private String message;

    DramaException(String message) {
        super(message);
    }

    public DramaException(IErrorCode iErrorCode) {
        super(iErrorCode.getCode() + "-" + iErrorCode.getErrorMessage());
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getErrorMessage();
    }

    public String getCode() {
        return code;
    }

}
