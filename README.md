# community
本项目是一个基于SpringBoot的社区平台，实现了牛客网讨论区的功能。实现了邮箱注册、验证码登录、发帖、评论、私信、点赞、关注、统计网站访问次数等功能，数据库使用Mybatis、Redis，使用Kafka构建系统通知，使用Elasticsearch构建全文搜索功能。

## 环境搭建
sprigboot + mybaits + maven + mysql + redis

## 功能模块概览
| 功能模块        | 是否完成    |
| --------   | -----:   |
| 注册/登陆        |   ✔    |
| 站内信        |  ✔     |
| 推送问题        | ❌      |
| 敏感词过滤        | ✔      |
| 评论中心        | ✔      |
| 解答赞踩        | ✔      |
| 私信        | ✔      |
| 异步队列        | ✔      |
| 邮件发送        | ✔       |
| 问题关注        | ✔       |
| 用户互关        | ✔       |
| 推送时间线        | ❌      |
| 搜索            | ❌       |