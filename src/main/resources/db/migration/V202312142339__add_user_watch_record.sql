CREATE TABLE user_watch_drama_record(
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    drama_id VARCHAR(20) NOT NULL COMMENT '短剧ID',
    drama_index TINYINT(1) NOT NULL COMMENT '当前观看的短剧所属集数',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(id)
) COMMENT = '系统配置表';