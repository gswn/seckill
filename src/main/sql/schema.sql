-- 数据库初始化脚本
--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;
--创建秒杀库存表
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar (120) Not NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL COMMENT '秒杀开启时间',
`end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
insert into seckill(name, number, start_time, end_time)
values
    ('1000元秒杀6', 100, '2022-01-07 00:00:00','2022-01-10 00:00:00'),
    ('500元秒杀7', 200, '2022-01-07 00:00:00','2022-01-10 00:00:00'),
    ('300元秒杀8', 300, '2022-01-07 00:00:00','2022-01-10 00:00:00'),
    ('200元秒杀9', 400, '2022-01-07 00:00:00','2022-01-10 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
create table success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标示：-1：无效 0：成功 1：已付款 2：已发货',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id, user_phone),/*联合主键*/
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表'

-- 连接数据库控制台
mysql -uroot -p

-- 为什么手写DDL
-- schema.sql记录每次上线的DDL修改
-- 上线v1.1
ALTER TABLE seckill
DROP INDEX idx_create_time,
ADD index idx_c_s(start_time,create_time);

-- 上线v1.2
-- ddl

--show tables;查看表
-- show create table seckill\G 查看创建表

-- 账户root 密码!QAZxsw2
-- 增加访问host：update user set host = '%' where user = 'root';
-- 修改用户密码：ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'test';