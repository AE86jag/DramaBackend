package com.spmystery.episode.exception;

public class DramaException extends RuntimeException {

    private String code;

    private String message;

    DramaException(String message) {
        super(message);
    }

    public DramaException(IErrorCode iErrorCode) {
        super(iErrorCode.getErrorMessage());
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getErrorMessage();
    }

    public DramaException(IErrorCode iErrorCode, Object param) {
        super(String.format(iErrorCode.getCode() + "-" + iErrorCode.getErrorMessage(), param));
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getErrorMessage();
    }

    public DramaException(IErrorCode iErrorCode, Object param1, Object param2) {
        super(String.format(iErrorCode.getErrorMessage(), param1, param2));
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getErrorMessage();
    }

    public String getCode() {
        return code;
    }

}
