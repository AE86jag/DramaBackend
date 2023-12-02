CREATE TABLE user(
    id VARCHAR(32) NOT NULL COMMENT '主键',
    mobile CHAR(11) COMMENT '手机号',
    nickname VARCHAR(50) COMMENT '微信昵称',
    open_id VARCHAR(50) NOT NULL COMMENT '微信OpenId',
    union_id VARCHAR(50) COMMENT '微信UnionId',
    head_img_url VARCHAR(500) COMMENT '微信头像地址',
    province VARCHAR(50) COMMENT '省',
    city VARCHAR(50) COMMENT '市',
    country VARCHAR(50) COMMENT '县',
    sex TINYINT(1) COMMENT '性别, 0:女, 1:男',
    `level` TINYINT(1) COMMENT '用户等级',
    status TINYINT(1) DEFAULT 1 COMMENT '用户状态, 0:无效, 1:正常',
    invite_user_id VARCHAR(32) COMMENT '邀请用户ID',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`id`)
) COMMENT = '用户表';

CREATE TABLE token(
    id VARCHAR(32) NOT NULL COMMENT '主键',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    expire_time DATETIME NOT NULL COMMENT 'token过期时间',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`id`)
) COMMENT = '用户登录信息表';