package me.xavier.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardTxEvent {

    private String txHash;
    private String from;
    private String to;
    private String contract;
    private String eventName;
    private Map<String, Object> eventParams; // 如 {amountIn: 1000, tokenIn: USDC}
    private Instant blockTimestamp;
}
