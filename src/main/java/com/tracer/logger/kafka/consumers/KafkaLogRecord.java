package com.tracer.logger.kafka.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;


public class KafkaLogRecord {
    private UUID id;
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

    private Date createdAt;
    private Date updatedAt;


    public KafkaLogRecord(ConsumerRecord<String, String> record) {
        this.message = "LogConsumerRecord";
        this.topic = record.topic();
        this.key = record.key();
        this.value = record.value();
        this.partition = record.partition();
        this.offset = record.offset();
        this.timestamp = String.valueOf(record.timestamp());
        this.timestampType = record.timestampType().toString();
        this.serializedKeySize = record.serializedKeySize();
        this.serializedValueSize = record.serializedValueSize();
        this.headers = record.headers().toString();
        this.leaderEpoch = record.leaderEpoch().toString();
    }

    public KafkaLogRecord(UUID id, String message, String topic, String key, String value, int partition, long offset, String timestamp, String timestampType, int serializedKeySize, int serializedValueSize, String headers, String leaderEpoch) {
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

    public KafkaLogRecord(String message, String topic, String key, String value, int partition, long offset, String timestamp, String timestampType, int serializedKeySize, int serializedValueSize, String headers, String leaderEpoch) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        if (o == null || getClass() != o.getClass()) return false;
        KafkaLogRecord that = (KafkaLogRecord) o;
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
