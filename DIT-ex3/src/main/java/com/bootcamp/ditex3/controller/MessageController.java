package com.bootcamp.ditex3.controller;

import com.bootcamp.ditex3.entity.Message;
import com.bootcamp.ditex3.service.MessageService;
import com.bootcamp.ditex3.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController {

    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/offer")
    public ResponseEntity<String> offerMessage(@Valid @RequestBody Message message) {
        OperationResult result = messageService.offer(message);
        return new ResponseEntity<>(result.getStatus());
    }

    @GetMapping("/poll")
    public ResponseEntity<Message> pollMessage() {
        Map.Entry <Message, OperationResult> result = messageService.poll();
        return new ResponseEntity<>(result.getKey(), result.getValue().getStatus());
    }

    @GetMapping("/peek")
    public ResponseEntity<Message> peekMessage() {
        Map.Entry <Message, OperationResult> result = messageService.peek();
        return new ResponseEntity<>(result.getKey(), result.getValue().getStatus());
    }

    @GetMapping("/peekMax")
    public ResponseEntity<Message> peekMaxMessage() {
        Map.Entry <Message, OperationResult> result = messageService.peekMax();
        return new ResponseEntity<>(result.getKey(), result.getValue().getStatus());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> peekAllMessages() {
        Map.Entry <List<Message>, OperationResult> result = messageService.all();
        return new ResponseEntity<>(result.getKey(), result.getValue().getStatus());
    }

}
