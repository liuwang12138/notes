# Zookeeper

zookeeper主要用于分布式协调

- 扩展性

  - 角色

    - Leader
    - Follower
    - Observer

  - 读写分离

    - 只有Leader可以写，其他可以查

    - 只有Follower才能选举

    - Observer能放大查询能力

    - ```
      zoo.cfg
      server.1=node01:2888:3888
      server.2=node02:2888:3888
      server.3=node03:2888:3888
      server.4=node04:2888:3888:observer
      ```

    - 一般让Follower只有几台就够了

- 可靠性

  - <font color="red">攘外必先安内</font>
  - 可靠性来自其快速恢复的能力
    - 一个Leader挂了会在很短的时间内恢复
    - 过程中，节点不再堆外提供服务

- 时序性
- 快速

## Paxos - Zookeeper的灵魂

- 是一个基于消息传递的一致性算法
- 前提：没有拜占庭将军问题，就是说Paxos只有在一个可信的计算环境中才能成立，这个环境是不会被入侵所破坏的

## Zookeeper写的流程

1. Client向Follower发出写请求
2. Follower向Leader发出写请求
3. Leader判断Zid是否合法（是否大于目前的Zid）
4. Leader向所有Follower写log
5. Follower向Leader回ok
6. Leader在接收过半的ok时，向所有Follower发出write命令
7. Follower向Leader回ok
8. Leader向请求Follower回ok
9. Follower向Client回ok

## Zookeeper选新Leader的流程

### 场景

1. 第一次启动集群
2. 重启集群，leader挂了之后

> 每个节点自己会有myid和Zxid

### 选新Leader的标准

​	经验最丰富。即数据最全，即Zxid比较大

### ZK选举的过程

1. 3888是选举端口，可以进行两两通信
2. 只要任何人投票，都会触发那个准Leader发起自己的投票
3. 推选制：先比较zxid，如果zxid相同，再比较myid

### Watch

当发生增删改的时候，回发生回调通知客户端

## Zookeeper实现分布式锁

1. 争抢锁，只有一个人能获得锁

2. 获得锁的人可能会出问题 -> 临时节点(session)

3. 获得锁的人成功了，释放锁

4. 锁被释放、删除，别人怎么知道？

   a. 主动轮询，心跳  ->  弊端：延迟、压力

   b. watch：解决延迟问题  ->  弊端：zookeeper会回调其他所有人，然后所有人又会产生新一轮的抢锁

   c. sequence(序列节点) + watch：watch谁？watch前一个，最小的获得锁。一旦最小的释放了锁，成本：zk只给第二个发事件回调