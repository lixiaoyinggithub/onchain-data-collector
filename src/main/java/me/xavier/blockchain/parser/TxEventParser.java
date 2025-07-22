package me.xavier.blockchain.parser;

import me.xavier.blockchain.model.StandardTxEvent;
import org.web3j.protocol.core.methods.response.Log;

public interface TxEventParser {
    /**
     * 判断该 parser 是否支持当前事件
     * @param event 合约事件日志
     */
    boolean supports(Log event);

    /**
     * 从合约日志中提取标准交易事件结构
     * @param event 日志对象
     * @return 标准化交易事件（如 Swap, Mint, Stake）
     */
    StandardTxEvent parse(Log event);

}
