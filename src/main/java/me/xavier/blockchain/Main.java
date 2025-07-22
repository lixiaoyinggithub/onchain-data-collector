package me.xavier.blockchain;

import me.xavier.blockchain.impl.UniswapEventListener;

import java.net.ConnectException;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws ConnectException {

        // 示例：UniswapV3 USDC/ETH 池子
//        String websocketUrl = "wss://mainnet.infura.io/ws/v3/47e689b3b02344138ee507e3dcf11388"; infura.io
        String websocketUrl = "wss://eth-mainnet.g.alchemy.com/v2/-9br8S3ZUvDGwI-0GeMxOyGxBNjA5TqP"; //alchemy
        String poolAddress = "0x8ad599c3a0ff1de082011efddc58f1908eb6e6d8";

        UniswapEventListener listener = new UniswapEventListener(websocketUrl, Arrays.asList(poolAddress));

        listener.startListening(event -> {
            System.out.println("Received Event: " + event);
        });

    }
}