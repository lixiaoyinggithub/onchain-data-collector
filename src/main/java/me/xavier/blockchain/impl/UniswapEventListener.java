package me.xavier.blockchain.impl;

import me.xavier.blockchain.listener.ContractEventListener;
import me.xavier.blockchain.model.ContractConfig;
import me.xavier.blockchain.model.StandardTxEvent;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.websocket.WebSocketService;

import java.net.ConnectException;
import java.util.List;
import java.util.function.Consumer;

public class UniswapEventListener implements ContractEventListener {
    private final Web3j web3j;
    private final List<String> contractAddresses;
    private final UniswapV3Parser parser = new UniswapV3Parser();

    public UniswapEventListener(String websocketUrl, List<String> contractAddresses) throws ConnectException {
        WebSocketService service = new WebSocketService(websocketUrl, true);
        service.connect();
        this.web3j = Web3j.build(service);
        this.contractAddresses = contractAddresses;
    }

    @Override
    public void startListening(Consumer<StandardTxEvent> callback) {
        EthFilter filter = new EthFilter(
                DefaultBlockParameterName.LATEST,
                DefaultBlockParameterName.LATEST,
                contractAddresses
        );

        web3j.ethLogFlowable(filter).subscribe(log -> handleLog(log, callback),throwable -> {
            System.err.println("订阅失败：" + throwable.getMessage());
            throwable.printStackTrace();
        });
    }

    private void handleLog(Log log, Consumer<StandardTxEvent> callback) {
        if (parser.supports(log)) {
            StandardTxEvent event = parser.parse(log);
            if (event != null) {
                callback.accept(event);
            }
        }
    }

    @Override
    public void register(ContractConfig config) {

    }

    @Override
    public void stopListening() {

    }
}
