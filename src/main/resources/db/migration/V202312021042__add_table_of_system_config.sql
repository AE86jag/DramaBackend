CREATE TABLE system_config(
    `key` VARCHAR(50) UNIQUE NOT NULL COMMENT '参数键',
    `value` VARCHAR(100) COMMENT '参数值',
    `group` VARCHAR(50) COMMENT '键值对所属组',
    `remark` VARCHAR(100) COMMENT '参数备注',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`key`)
) COMMENT = '系统配置表';
INSERT INTO system_config(`key`, `value`, `group`, `remark`) VALUES('user_register_reward', '90', 'register', '用户注册');