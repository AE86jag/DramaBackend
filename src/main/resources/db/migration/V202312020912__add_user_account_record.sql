CREATE TABLE user_account_record(
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    change_type CHAR(1) NOT NULL COMMENT '变更类型, C: 提现; D: 看完短剧; A: 看完广告; I: 邀请; R:注册; ',
    change_message VARCHAR(100) NOT NULL COMMENT '账户变更原因',
    change_amount DECIMAL(8, 2) NOT NULL COMMENT '变更金额',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`id`)
) COMMENT = '用户账户记录表';

CREATE INDEX index_user_id ON user_account_record(user_id);