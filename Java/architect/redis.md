# Redis

## Guide

常识：

1. 磁盘比内存在寻址上慢了10W倍
2. 磁盘有磁道和扇区，一扇区512Byte。存储单位小，带来成本变大 -> 索引

Question：

​		数据库，表很大，性能会下降吗？

​				if 表有索引，增删改会变慢

​		查询速度：

			1.  一个或少量查询依然很快
   			2.  并发大的时候会受磁盘带宽影响速度

数据在磁盘和内存中的体积不一样，内存中会更小

## redis 和 memcached

- redis - 秒级10W操作
- redis和memcached
  - 两者都是key-value类型的，但memcached中的value没有类型的概念，redis中的value有类型的概念
  - 类型不是很重要，memcached可以通过json存储各种复杂的数据类型
  - 当client发送请求，想要从缓存的key-value中取value中的某个元素时，memcached需要返回value所有的数据到client。server端需要有网卡IO，client要有你实现的代码去解码。redis的server中对每种类型都有自己的方法
  - <strong><font color="red">计算向数据移动</font></strong>

## redis 安装

### 环境准备

- centos 6.x
- redis 官网 5.x

### 安装步骤

1. yum install wget

2. wget ${address}

3. yum install gcc

4. make (make distclean)

5. make install PREFIX=/opt/msb/redis5 (安装到路径)

6. 安装环境变量：vi /etc/profile

   ​		export REDIS_HOME=/opt/msb/redis5

   ​		export PATH=$PATH:$REDIS_HOME/bin

   ​		

   ​		source /etc/profile

7. 进入源码utils目录：

   ​	./install_server.sh