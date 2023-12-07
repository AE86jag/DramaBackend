CREATE TABLE user_role(
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    role_id CHAR(20) NOT NULL COMMENT '角色ID',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间'
) COMMENT = '用户账户表';