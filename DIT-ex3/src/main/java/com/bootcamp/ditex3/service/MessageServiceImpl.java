package com.bootcamp.ditex3.service;

import com.bootcamp.ditex3.dao.DAO;
import com.bootcamp.ditex3.entity.Message;
import com.bootcamp.ditex3.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    private final DAO <Message> messageDAO;

    @Autowired
    public MessageServiceImpl(@Qualifier("messageDAO") DAO<Message> messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    @Transactional
    public OperationResult offer(Message message) {
        Message checkMessage = messageDAO.get(message.getId());
        OperationResult result;
        if (checkMessage != null) {
            if (checkMessage.getMsg().equals(message.getMsg()))
                return OperationResult.EXIST;
            result = OperationResult.EXIST;
            checkMessage.setIsModified(true);
            checkMessage.setIsInQueue(false);
            messageDAO.save(checkMessage);
        }
        else {
            result = OperationResult.OK;
        }
        messageDAO.save(message);
        return result;
    }

    @Override
    @Transactional
    public Map.Entry<Message, OperationResult> poll() {
        Message message = messageDAO.getFirst();
        OperationResult result;
        if (message == null)
            result = OperationResult.NOT_FOUND;
        else {
            message.setIsInQueue(false);
            messageDAO.save(message);
            result = OperationResult.OK;
        }
        return new AbstractMap.SimpleImmutableEntry<>(message, result);
    }

    @Override
    public Map.Entry<Message, OperationResult> peek() {
        Message message = messageDAO.getFirst();
        OperationResult result;
        if (message == null)
            result = OperationResult.NOT_FOUND;
        else {
            result = OperationResult.OK;
        }
        return new AbstractMap.SimpleImmutableEntry<>(message, result);
    }

    @Override
    public Map.Entry<Message, OperationResult> peekMax() {
        Message message = messageDAO.getLast();
        OperationResult result;
        if (message == null)
            result = OperationResult.NOT_FOUND;
        else {
            result = OperationResult.OK;
        }
        return new AbstractMap.SimpleImmutableEntry<>(message, result);
    }

    @Override
    public Map.Entry<List<Message>, OperationResult> all() {
        List<Message> messages = messageDAO.getAll();
        OperationResult result;
        if (messages == null)
            result = OperationResult.NOT_FOUND;
        else {
            result = OperationResult.OK;
        }
        return new AbstractMap.SimpleImmutableEntry<>(messages, result);
    }
}
