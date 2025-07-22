package me.xavier.blockchain.coordinator;

import me.xavier.blockchain.feature.FeatureExtractor;
import me.xavier.blockchain.listener.ContractEventListener;
import me.xavier.blockchain.parser.TxEventParser;
import me.xavier.blockchain.sink.DataSink;

/**
 * @author Xavier
 */
public interface ProcessorCoordinator {
    /**
     * 启动整个采集与处理流程
     */
    void start();

    /**
     * 添加合约监听器
     */
    void addContractListener(ContractEventListener listener);

    /**
     * 添加数据解析器
     *
     * @param parser 事件解析器
     */
    void addParser(TxEventParser parser);

    /**
     * 添加行为特征提取
     *
     * @param extractor 特征提取器
     */
    void addFeatureExtractor(FeatureExtractor extractor);

    /**
     * 添加数据sink
     *
     * @param sink
     */
    void addSink(DataSink<?> sink);

}
