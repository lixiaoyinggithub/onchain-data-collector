package me.xavier.blockchain.sink;

public interface DataSink<T> {
    /**
     * 输出清洗后的数据
     * @param data 特征数据或事件数据
     */
    void publish(T data);

}
