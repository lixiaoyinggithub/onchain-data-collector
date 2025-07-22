package me.xavier.blockchain.impl;

import me.xavier.blockchain.EventUtils;
import me.xavier.blockchain.model.StandardTxEvent;
import me.xavier.blockchain.parser.TxEventParser;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int24;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.Log;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniswapV3Parser implements TxEventParser {

    private static final Event SWAP_EVENT = new Event("Swap", Arrays.asList(
            TypeReference.create(Address.class, true),   // sender (indexed)
            TypeReference.create(Address.class, true),   // recipient (indexed)
            TypeReference.create(Int256.class),          // amount0
            TypeReference.create(Int256.class),          // amount1
            TypeReference.create(Uint160.class),         // sqrtPriceX96
            TypeReference.create(Uint256.class),      // liquidity
            TypeReference.create(Int24.class)       // tick
    ));

    private static final String SWAP_TOPIC = EventEncoder.encode(SWAP_EVENT);

    @Override
    public boolean supports(Log log) {
        return log.getTopics() != null && log.getTopics().size() > 0
                && SWAP_TOPIC.equals(log.getTopics().get(0));
    }

    @Override
    public StandardTxEvent parse(Log log) {
        try {
            List<String> topics = log.getTopics();
            String sender = EventUtils.parseAddressFromTopic(topics.get(1));
            String recipient = EventUtils.parseAddressFromTopic(topics.get(2));

            List<TypeReference<Type>> nonIndexedParams = SWAP_EVENT.getNonIndexedParameters();
            List<Type> decoded = FunctionReturnDecoder.decode(log.getData(), nonIndexedParams);

            BigInteger amount0 = (BigInteger) decoded.get(0).getValue();
            BigInteger amount1 = (BigInteger) decoded.get(1).getValue();

            Map<String, Object> eventParams = new HashMap<>();
            eventParams.put("sender", sender);
            eventParams.put("recipient", recipient);
            eventParams.put("amount0", amount0);
            eventParams.put("amount1", amount1);

            StandardTxEvent event = new StandardTxEvent();
            event.setTxHash(log.getTransactionHash());
            event.setFrom(sender);
            event.setTo(recipient);
            event.setContract(log.getAddress());
            event.setEventName("Swap");
            event.setEventParams(eventParams);
            event.setBlockTimestamp(Instant.ofEpochSecond(fetchBlockTimestamp(log.getBlockNumber()))); // 需调用缓存区块接口

            return event;
        } catch (Exception e) {
            // 可记录异常用于监控
            System.out.println("解析失败");

            return null;
        }
    }

    /**
     * 可通过 Redis 缓存 + Web3 接口获取区块时间戳
     */
    private long fetchBlockTimestamp(BigInteger blockNumber) {
        // TODO: 从 Web3 API 查询区块信息，返回 timestamp
        return Instant.now().getEpochSecond();
    }
}