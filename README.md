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

需要一个系统日终对账


#### 提现
一天只能提现一次
提现申请时判断是否看完整部短剧分两步，第一步先请求后台查询用户观看的短剧ID，根据每个短剧ID，调用穿山甲SDK查询短剧

#### 短剧
短剧回调参数
{video_duration=115, video_height=960, script_author=, video_width=540, index=1, 
title=神医的绝代双娇, type=都市, script_name=, total=60, video_size=9171771, 
group_id=7264515034716684861, cover_image=https://content.volccdn.com/obj/feedcoop/skit/0547.jpg, drama_id=547, status=0, 
desc=李不凡学得一身好医术，下山行医并完成所指配的婚约。李不凡辗转至唐家，却意外卷入唐家双胞胎姐妹的争夺漩涡之中。在面临选择的同时，李不凡意外发现当年陷害自己父亲的人也逐渐浮出水面。看少年神医如何救治唐家众人，替父报仇的同时收获爱情。}



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
