package com.bootcamp.ditex3.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queue_id")
    int queueId;

    @NotNull(message = "Id is mandatory")
    @Column(name = "id")
    int id;

    @NotBlank(message = "Id is mandatory")
    @Size(max = 4000)
    @Column(name = "msg")
    String msg;

    @Column(name = "in_queue")
    boolean isInQueue;

    @Column(name = "modified")
    boolean isModified;

    public Message() {
        isInQueue = true;
        isModified = false;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int msgId) {
        this.id = msgId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getIsInQueue() {
        return isInQueue;
    }

    public void setIsInQueue(boolean inQueue) {
        isInQueue = inQueue;
    }

    public boolean getIsModified() {
        return isModified;
    }

    public void setIsModified(boolean modified) {
        isModified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "queueId=" + queueId +
                ", Id=" + id +
                ", msg='" + msg + '\'' +
                ", isInQueue=" + isInQueue +
                ", isModified=" + isModified +
                '}';
    }
}
