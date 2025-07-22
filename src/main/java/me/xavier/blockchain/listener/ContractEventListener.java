package me.xavier.blockchain.listener;

import me.xavier.blockchain.model.ContractConfig;
import me.xavier.blockchain.model.StandardTxEvent;

import java.util.function.Consumer;

public interface ContractEventListener {
    /**
     * 注册监听任务
     *
     * @param config 合约配置（地址、ABI、事件等）
     */
    void register(ContractConfig config);

    /**
     * 启动监听（订阅链上事件）
     */
    void startListening(Consumer<StandardTxEvent> callback);

    /**
     * 停止监听
     */
    void stopListening();
}
