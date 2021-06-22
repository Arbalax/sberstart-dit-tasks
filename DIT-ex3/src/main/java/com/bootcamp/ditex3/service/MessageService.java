package com.bootcamp.ditex3.service;

import com.bootcamp.ditex3.entity.Message;
import com.bootcamp.ditex3.util.OperationResult;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public interface MessageService {

    public OperationResult offer(Message message);

    public Map.Entry<Message, OperationResult> poll();

    public Map.Entry<Message, OperationResult> peek();

    public Map.Entry<Message, OperationResult> peekMax();

    public Map.Entry<List<Message>, OperationResult> all();
}
