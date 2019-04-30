package com.wildanka.websocketmvvm.model.service;

import java.net.URISyntaxException;
import java.util.EventListener;

public interface EventService {
    void connect(String username) throws URISyntaxException;

    void disconnect();

    Flowable<ChatMessage> sendMessage(ChatMessage chatMessage);

    void setEventListener(EventListener eventListener);
}
