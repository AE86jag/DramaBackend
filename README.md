# 介绍
短剧应用后台，用户管理、积分管理、提现管理等功能

# 软件架构
## 数据表
- `user`(用户表): 保存用户基本信息
- `user_role`(用户角色关系表): 保存用户的角色，一个用户可以有多个角色
- `role`(用户角色表): 保存系统角色
- `user_account_record`(用户账户记录表): 保存用户账户变动的记录，什么行为增加了金额，提现减少了金额
- `config`(系统配置表): 配置系统一些动态参数
- `drama_view_record`(短剧观看记录表): 记录观看记录，防止重复刷账户
- `user_cash_out_account`(用户提现账户): 保存用户提现账户结果
- `user_cash_out_account_application`(用户绑定提现账户申请): 保存用户绑定提现账户申请
- `user_cash_out_application`(用户提现申请): 保存用户提现账户绑定申请
- `token`(用户登录token): 保存用户的登录信息，每次使用的时候判断是否过期



#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
