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
    DO008("未达到观看%s个不同短剧数量条件, 当前查看个数为%s个"),
    DO009("短剧观看记录参数为空"),
    DO010("用户观看记录异常，涉嫌刷数据"),
    DO011("未达到观看%s部完整短剧, 当前已观看完整短剧部数%s"),
    DO012("用户未绑定提现账户"),
    DO013("提现申请记录未找到"),
    DO014("提现申请记录状态异常"),
    DO015("提现参数异常"),
    DO016("用户余额小于提现金额，无法提现"),
    DO017("提现金额必须大于0"),
    DO018("提现拒绝原因不能为空"),

    //用户相关, 第二位是U
    DU001("用户不存在"),
    DU002("用户已失效"),
    DU003("用户已绑定提现账户"),

    //短剧相关
    DD001("短剧ID不能为空"),
    DD002("短剧集数不能为空"),
    DD003("短剧总集数不能为空")
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
