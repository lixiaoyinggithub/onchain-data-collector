package me.xavier.blockchain.feature;

import me.xavier.blockchain.model.AddressFeature;
import me.xavier.blockchain.model.StandardTxEvent;

import java.util.List;

public interface FeatureExtractor {
    /**
     * 从标准交易事件中提取地址行为特征
     * @param address 钱包地址
     * @param txEvents 该地址相关交易事件列表
     * @return 结构化特征对象
     */
    AddressFeature extract(String address, List<StandardTxEvent> txEvents);

}
