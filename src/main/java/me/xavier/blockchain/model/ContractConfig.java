package me.xavier.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractConfig {
    private String contractName;
    private String address;
    private String chainId;
    private String abiJson; // ABI 内容
    private List<String> eventNames;
}
