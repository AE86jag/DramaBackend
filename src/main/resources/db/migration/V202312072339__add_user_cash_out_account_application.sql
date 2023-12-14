CREATE TABLE user_cash_out_account_application(
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    amount DECIMAL(8, 2) NOT NULL COMMENT '提现金额',
    status TINYINT(1) NOT NULL COMMENT '审批状态, 0:待审批, 1:审批通过, 2: 审批拒绝',
    `level` TINYINT(1) NOT NULL COMMENT '提现级别, 1到N级，多少级根据配置',
    approve_user_id VARCHAR(32) COMMENT '用户ID',
    approve_user_name VARCHAR(50) COMMENT '用户昵称',
    approve_time DATETIME COMMENT '审批时间',
    reject_reason VARCHAR(200) COMMENT '拒绝原因',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(id)
) COMMENT = '系统配置表';