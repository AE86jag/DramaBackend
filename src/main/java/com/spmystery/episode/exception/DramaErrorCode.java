package com.spmystery.episode.exception;

public enum DramaErrorCode implements IErrorCode {
    SUCCESS("成功"),
    //账户相关，第二位是A
    DA001("余额不足"),

    //配置相关，第二位是C
    DC001("每日提现次数未配置或配置错误"),
    DC002("提现规则未配置或配置错误"),

    //提现相关，第二位是O
    DO001("提现规则为空，请选择正常提现金额"),
    DO002("配置不存在"),
    DO003("参数配置错误"),
    DO004("用户等级不符合提现要求"),
    DO005("今日提现次数超过该提现等级提现上限"),
    DO006("今日提现次数超过提现总次数"),
    DO007("当前提现金额和待审批提现金额大于余额，无法再申请提现"),

    //用户相关, 第二位是U
    DU001("用户不存在"),
    DU002("用户已失效")
    ;

    DramaErrorCode(String message) {
        this.message = message;
    }


    private String message;


    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}
