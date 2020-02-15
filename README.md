# community

本项目是一个基于SpringBoot的社区平台，实现了牛客网讨论区的功能。实现了邮箱注册、验证码登录、发帖、评论、私信、点赞、关注、统计网站访问次数等功能，数据库使用Mybatis、Redis，使用Kafka构建系统通知，使用Elasticsearch构建全文搜索功能。同时实现生成pdf文件、上传云服务器等功能，最后将项目部署到了阿里ECS云服务器上（ubuntu系统）。

## 环境搭建
sprigboot + mybaits + maven + mysql + redis

## 功能模块概览
| 功能模块        | 是否完成    |
| --------   | -----:   |
| 注册/登陆        |   ✔    |
| 站内信        |  ✔     |
| 敏感词过滤        | ✔      |
| 评论中心        | ✔      |
| 解答赞踩        | ✔      |
| 私信        | ✔      |
| 异步队列        | ✔      |
| 邮件发送        | ✔       |
| 问题关注        | ✔       |
| 用户互关        | ✔       |
| 全文功能          | ✔      |

## 项目总结

* Spring Boot
* **Spring**
* Spring MVC、Spring Mybatis、**Spring Security**
* 权限@会话管理
  * 注册、登录、退出、状态、设置
  * Spring Email、**Interceptor**
* 核心@敏感词、@事务
  * 首页、帖子、评论、私信、异常、日志
  * Advice、**AOP**、**Transaction**
* 性能@数据结构
  * 点赞、关注
  * **Redis**
* 通知
  * Kafka
* 搜索@索引
  * 全文搜索
  * Elasticsearch
