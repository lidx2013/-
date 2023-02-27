## CAP

eureka 为什么ap

## 三级缓存

register    - >    readWriteCacheMap    - >   readOnlyCacheMap

## 优化

```
server:
 #自我保护
 enbale-self-preservation: false
 #自我保护阈值
 renewal-percent-threshold: 0.85
 # 剔除服务时间间隔
 eviction-interval-timer-in-ms: 100
 # 关闭从readOnly读注册表
 use-read-only-response-cache: false
 # readWrite 和 readOnly 同步时间间隔
 response-cache-update-interval-ms: 100
```



## 关键类

applicationResource