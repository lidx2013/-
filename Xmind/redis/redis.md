

## redis底层数据结构

### 字符串

#### sds 数据类型

```
type{
	free //剩余空间
    len:12	 //长度 大于1024*1024
	char buff[]=
}
```

##### 特点

- 二进制数据安全

### Hasp(K-V)

#### dic 数据类型

- 数组
- 链表

```
dictEntry

 
typedef struct redisObject {

	 unsigned type:4 //类型
	 unsigned encoding: //编码
	 unsigned lru   //过期策略
	 int refcount
	 void *ptr;   //具体指向的指针
}
```



### List

#### 双端链表+ziplist

```
ziplist 255作为最后的长度
```









## reids 穿透、击穿、雪崩

### 穿透
```
    1. 没有这笔数据
    2. key null
    3. 布隆过滤器
```
### 击穿
```
    1. 热点key过期（没有被缓存的）
    2. 数据库有
    3. 大量的并发，redis没有缓存
    4. 必须由一个redis 提供锁
```
### 雪崩
```
    1. key 的数量是N 个
    2. AKF 的分治。吧 
```


### 解决方法
```
    1. 并不知道请求是不是并发
    2. 保障DB的压力，有效请求
    
    1. 请求redis，肯定没有
    2. 大家抢锁 O(只有发生redis取不到)
        2.1 抢上的碰DB O(1)
        2.2 没抢上的sleep
    3. DB 更新redis O(1)
    4. sleep回到第一步
```