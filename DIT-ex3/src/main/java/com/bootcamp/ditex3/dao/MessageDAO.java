package com.bootcamp.ditex3.dao;

import com.bootcamp.ditex3.entity.Message;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class MessageDAO implements DAO<Message> {

    private final EntityManager entityManager;

    @Autowired
    public MessageDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Message get(int msgId) {
        Session session = entityManager.unwrap(Session.class);
        Query<Message> query = session.createQuery("from Message where id = :requestId and isInQueue = true", Message.class);
        query.setParameter("requestId", msgId);
        List<Message> messages = query.getResultList();
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(0);
    }

    @Override
    public Message getFirst() {
        Session session = entityManager.unwrap(Session.class);
        Query<Integer> queryQueueId = session.createQuery("select min(queueId) from Message where isInQueue = true");
        Query<Message> query = session.createQuery("from Message where queueId = :queueId", Message.class);
        int queueId = queryQueueId.getResultList().get(0);
        query.setParameter("queueId", queueId);
        List<Message> messages = query.getResultList();
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(0);
    }

    @Override
    public Message getLast() {
        Session session = entityManager.unwrap(Session.class);
        Query<Integer> queryQueueId = session.createQuery("select max(queueId) from Message where isInQueue = true");
        Query<Message> query = session.createQuery("from Message where queueId = :queueId", Message.class);
        int queueId = queryQueueId.getResultList().get(0);
        query.setParameter("queueId", queueId);
        List<Message> messages = query.getResultList();
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(0);
    }

    @Override
    public List<Message> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Message> query = session.createQuery("from Message where isInQueue = true", Message.class);
        List<Message> messages = query.getResultList();
        if (messages.isEmpty()) {
            return null;
        }
        return messages;
    }

    @Override
    public void save(Message message) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(message);
    }

}
