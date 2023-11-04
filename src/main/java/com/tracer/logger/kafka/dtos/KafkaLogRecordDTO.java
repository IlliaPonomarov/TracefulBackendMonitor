package com.tracer.logger.kafka.dtos;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.Objects;
import java.util.UUID;

public class KafkaLogRecordDTO {
    private final UUID id;
    private String message;
    private String topic;
    private String key;
    private String value;
    private int partition;
    private long offset;
    private String timestamp;
    private String timestampType;
    private int serializedKeySize;
    private int serializedValueSize;
    private String headers;
    private String leaderEpoch;


    public KafkaLogRecordDTO(UUID id, String message, String topic, String key, String value, int partition, long offset, String timestamp, String timestampType, int serializedKeySize, int serializedValueSize, String headers, String leaderEpoch) {
        this.id = id;
        this.message = message;
        this.topic = topic;
        this.key = key;
        this.value = value;
        this.partition = partition;
        this.offset = offset;
        this.timestamp = timestamp;
        this.timestampType = timestampType;
        this.serializedKeySize = serializedKeySize;
        this.serializedValueSize = serializedValueSize;
        this.headers = headers;
        this.leaderEpoch = leaderEpoch;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestampType() {
        return timestampType;
    }

    public void setTimestampType(String timestampType) {
        this.timestampType = timestampType;
    }

    public int getSerializedKeySize() {
        return serializedKeySize;
    }

    public void setSerializedKeySize(int serializedKeySize) {
        this.serializedKeySize = serializedKeySize;
    }

    public int getSerializedValueSize() {
        return serializedValueSize;
    }

    public void setSerializedValueSize(int serializedValueSize) {
        this.serializedValueSize = serializedValueSize;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getLeaderEpoch() {
        return leaderEpoch;
    }

    public void setLeaderEpoch(String leaderEpoch) {
        this.leaderEpoch = leaderEpoch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KafkaLogRecordDTO that)) return false;

        return partition == that.partition && offset == that.offset && serializedKeySize == that.serializedKeySize && serializedValueSize == that.serializedValueSize && Objects.equals(id, that.id) && Objects.equals(message, that.message) && Objects.equals(topic, that.topic) && Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(timestamp, that.timestamp) && Objects.equals(timestampType, that.timestampType) && Objects.equals(headers, that.headers) && Objects.equals(leaderEpoch, that.leaderEpoch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, topic, key, value, partition, offset, timestamp, timestampType, serializedKeySize, serializedValueSize, headers, leaderEpoch);
    }

    @Override
    public String toString() {
        return "LogConsumerRecord{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", topic='" + topic + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", partition=" + partition +
                ", offset=" + offset +
                ", timestamp='" + timestamp + '\'' +
                ", timestampType='" + timestampType + '\'' +
                ", serializedKeySize=" + serializedKeySize +
                ", serializedValueSize=" + serializedValueSize +
                ", headers='" + headers + '\'' +
                ", leaderEpoch='" + leaderEpoch + '\'' +
                '}';
    }
}
