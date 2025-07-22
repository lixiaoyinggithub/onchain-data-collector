package me.xavier.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressFeature {
    private String address;
    private int totalTxCount;
    private int dexSwapCount;
    private double avgSwapValue;
    private Set<String> labels; // 如 ["dex_user", "whale"]
    private Instant lastActiveTime;

}
