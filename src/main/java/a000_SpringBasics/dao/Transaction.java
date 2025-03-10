package a000_SpringBasics.dao;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Chenyu Liu
 * @since 3/9/25 Sunday
 **/

public class Transaction {
    private UUID txId;
    private String name;
    private LocalDateTime timestamp;

    public UUID getTxId() {
        return txId;
    }

    public void setTxId(UUID txId) {
        this.txId = txId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
