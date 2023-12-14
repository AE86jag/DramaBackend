CREATE TABLE system_config(
    `key` VARCHAR(50) UNIQUE NOT NULL COMMENT '参数键',
    `value` VARCHAR(100) COMMENT '参数值',
    `group` VARCHAR(50) COMMENT '键值对所属组',
    `remark` VARCHAR(100) COMMENT '参数备注',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`key`)
) COMMENT = '系统配置表';
INSERT INTO system_config(`key`, `value`, `group`, `remark`) VALUES('user_register_reward', '90', 'register', '新用户注册，注册用户获得积分');
INSERT INTO system_config(`key`, `value`, `group`, `remark`) VALUES('invite_user_reward', '5', 'invite', '邀请用户注册，邀请人获得积分');
INSERT INTO system_config(`key`, `value`, `group`, `remark`) VALUES('user_watching_advertisements', '0.01', 'advertisement', '用户看完一个广告增加的积分');
INSERT INTO system_config(`key`, `value`, `group`, `remark`) VALUES('user_watching_one_drama', '0.01', 'drama', '用户看完一集短剧增加的积分');
INSERT INTO system_config(`key`, `value`, `group`, `remark`) VALUES('user_cash_out_count_per_day', '3', 'cash_out', '用户每日总的提现次数');
INSERT INTO system_config(`key`, `value`, `group`, `remark`) VALUES('system_cash_out_condition_config', '[{"level": 1, "amount": 0.3, "watchNumber": 3, "userLevel": 0, "dramaCount": 0, "cashOutCountPerDay": 3},{"level": 2, "amount": 0.5, "watchNumber": 10, "userLevel": 4, "dramaCount": 0, "cashOutCountPerDay": 3},{"level": 3, "amount": 100, "watchNumber": 20, "userLevel": 10, "dramaCount": 1, "cashOutCountPerDay": 1},{"level": 4, "amount": 200, "watchNumber": 30, "userLevel": 10, "dramaCount": 3, "cashOutCountPerDay": 1},{"level": 5, "amount": 300, "watchNumber": 50, "userLevel": 10, "dramaCount": 5, "cashOutCountPerDay": 1},{"level": 6, "amount": 500, "watchNumber": 100, "userLevel": 10, "dramaCount": 10, "cashOutCountPerDay": 1}]', 'cash_out', '系统提现规则配置');
INSERT INTO system_config(`key`, `value`, `group`, `remark`) VALUES('need_ad_counts_per_level', '100', 'cash_out', '用户每个等级所需要观看的广告数');
