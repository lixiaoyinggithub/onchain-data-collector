区块链学习项目

## 模块
- 事件监听
- 数据解析
- 特征提取
- 数据存储

## 可选增强方向（后续可扩展）
多事件解析器支持
可维护一个 Map<String, TxEventParser>，按 topic 动态路由
-  缓存区块时间戳
使用 LRU + Redis 缓存避免频繁 RPC 查询
-  Flink Source 封装
可封装为 SourceFunction<StandardTxEvent> 实现流处理
-  Kafka Sink 支持
解析事件后可推送至 Kafka，便于下游处理
-  指标上报
添加事件计数器 + 错误日志收集

